package com.p120189311.theendoflimbo.worldgen.tree.custom;

import com.p120189311.theendoflimbo.util.TheEndOfLimboTags;
import com.p120189311.theendoflimbo.worldgen.tree.TheEndOfLimboTrunkPlacerTypes;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

public class AbyssTrunkPlacer extends TrunkPlacer {
    public static final Codec<AbyssTrunkPlacer> CODEC = RecordCodecBuilder.create(instance ->
            trunkPlacerParts(instance).apply(instance, AbyssTrunkPlacer::new)
    );

    public AbyssTrunkPlacer(int p1, int p2, int p3) { super(p1, p2, p3); }

    @Override
    protected TrunkPlacerType<?> type() {
        return TheEndOfLimboTrunkPlacerTypes.ABYSS_TRUNK_PLACER.get();
    }

    /* ---------------------- Small helpers ---------------------- */
    private boolean isAirOrSmallObjects(LevelSimulatedReader reader, BlockPos pos) {
        return reader.isStateAtPosition(pos, state ->
                state.isAir() || state.canBeReplaced() || state.is(TheEndOfLimboTags.Blocks.REPLACEABLE_PLANTLIKE));
    }

    private static BlockPos findGround(LevelSimulatedReader reader, BlockPos probe) {
        BlockPos.MutableBlockPos m = probe.mutable();
        while (m.getY() > -64) {
            try {
                if (!reader.isStateAtPosition(m, state -> state.isAir())) break;
            } catch (RuntimeException e) {
                break; // reached unloaded region
            }
            m.move(Direction.DOWN);
        }
        return m.immutable();
    }

    private void safePlaceLog(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> replacer,
                              RandomSource rand, BlockPos pos, TreeConfiguration config) {
        try {
            if (isAirOrSmallObjects(reader, pos)) {
                replacer.accept(pos, config.trunkProvider.getState(rand, pos));
            }
        } catch (RuntimeException e) {
            // ignore "chunk out of bound" errors
        }
    }

    // Place 2x2 aligned to world axes, with base block as top-left corner
    private void place2x2Tilted(LevelSimulatedReader reader,
                                BiConsumer<BlockPos, BlockState> replacer,
                                RandomSource rand,
                                BlockPos base,
                                int tiltDir, // -1 = tilt toward negative Z, +1 = toward positive Z
                                TreeConfiguration config) {

        // lower row
        safePlaceLog(reader, replacer, rand, base, config);
        safePlaceLog(reader, replacer, rand, base.offset(1, 0, 0), config);

        // upper tilted row
        safePlaceLog(reader, replacer, rand, base.offset(0, 1, tiltDir), config);
        safePlaceLog(reader, replacer, rand, base.offset(1, 1, tiltDir), config);
    }

    // Place a 3x3 square centered on center
    private void place3x3(LevelSimulatedReader reader, BiConsumer<BlockPos, BlockState> replacer,
                          RandomSource rand, BlockPos center, TreeConfiguration config) {
        for (int ox = -1; ox <= 1; ox++) for (int oz = -1; oz <= 1; oz++)
            safePlaceLog(reader, replacer, rand, center.offset(ox, 0, oz), config);
    }

    private void generateStemRing(LevelSimulatedReader reader,
                                  BiConsumer<BlockPos, BlockState> replacer,
                                  RandomSource random,
                                  BlockPos trunkTopCenter,
                                  int radius,
                                  int height,
                                  TreeConfiguration config) {

        // number of segments (more = smoother ring)
        int segments = 8 + random.nextInt(6); // 8–13 segments

        for (int i = 0; i < segments; i++) {
            double angle = (Math.PI * 2.0 * i) / (double) segments;

            int dx = (int)Math.round(Math.cos(angle) * radius);
            int dz = (int)Math.round(Math.sin(angle) * radius);

            BlockPos p = trunkTopCenter.offset(dx, 0, dz);

            // place the node
            safePlaceLog(reader, replacer, random, p, config);

            // connect to previous segment to form a continuous ring
            double prevAngle = (Math.PI * 2.0 * (i - 1)) / (double) segments;
            int pdx = (int)Math.round(Math.cos(prevAngle) * radius);
            int pdz = (int)Math.round(Math.sin(prevAngle) * radius);
            BlockPos prev = trunkTopCenter.offset(pdx, 0, pdz);

            connectLogs(reader, replacer, random, prev, p, config);
        }
    }

    private void connectLogs(LevelSimulatedReader reader,
                             BiConsumer<BlockPos, BlockState> replacer,
                             RandomSource random,
                             BlockPos a, BlockPos b,
                             TreeConfiguration config) {

        int steps = Math.max(Math.abs(a.getX() - b.getX()), Math.abs(a.getZ() - b.getZ()));
        for (int step = 0; step <= steps; step++) {
            double t = step / (double)steps;
            int x = (int)Math.floor(a.getX() + (b.getX() - a.getX()) * t);
            int y = a.getY();
            int z = (int)Math.floor(a.getZ() + (b.getZ() - a.getZ()) * t);
            safePlaceLog(reader, replacer, random, new BlockPos(x, y, z), config);
        }
    }

    /* ---------------------- Top roots (parabolic) ---------------------- */
    private void generateTopRoot(LevelSimulatedReader reader,
                                 BiConsumer<BlockPos, BlockState> replacer,
                                 RandomSource rand,
                                 BlockPos trunkOrigin,
                                 double angle,
                                 int maxLen,
                                 int trunkHeight,
                                 TreeConfiguration config) {

        // 1) Starting height (top root zone)
        double frac = 0.15 + rand.nextDouble() * 0.15; // 0.15–0.30 of trunk height
        double startY = trunkOrigin.getY() + Math.max(1, Math.round(trunkHeight * frac));

        // 2) Starting X/Z (center of trunk)
        double startX = trunkOrigin.getX();
        double startZ = trunkOrigin.getZ();

        // 3) Root direction
        double dirX = Math.cos(angle);
        double dirZ = Math.sin(angle);

        // 4) Parabolic drop factor
        double factor = 0.28 + (rand.nextDouble() - 0.5) * 0.04;  // slightly flatter curve

        // 5) Noise used to “randomize” 2×2 orientation
        int orientX = rand.nextBoolean() ? 0 : 1;
        int orientZ = rand.nextBoolean() ? 0 : 1;

        // ------------------ FIRST SEGMENT (attaches to trunk) ------------------
        BlockPos first = new BlockPos(
                (int)Math.round(startX + dirX * 1.3),
                (int)Math.round(startY),
                (int)Math.round(startZ + dirZ * 1.3)
        );

        // tilt direction from angle, not from Z/X comparison
        int tiltDir;
        if (Math.abs(dirZ) > Math.abs(dirX))
            tiltDir = dirZ > 0 ? +1 : -1;
        else
            tiltDir = dirX > 0 ? +1 : -1;

        place2x2Tilted(reader, replacer, rand, first, tiltDir, config);

        // small reinforcement
        if (rand.nextFloat() < 0.25f)
            safePlaceLog(reader, replacer, rand, first.above(), config);

        // ------------------ PROGRESSIVE CURVE ------------------
        double prevX = first.getX();
        double prevZ = first.getZ();

        for (int step = 1; step <= maxLen; step++) {

            // outward march + jitter
            double fx = startX + dirX * step + (rand.nextDouble() - 0.5) * 0.20;
            double fz = startZ + dirZ * step + (rand.nextDouble() - 0.5) * 0.20;

            // curvature
            double dist = Math.sqrt((fx - startX) * (fx - startX)
                    + (fz - startZ) * (fz - startZ));
            double fy = startY - (factor * dist * dist * 0.8);  // slightly less sag

            BlockPos probe = new BlockPos(
                    (int)Math.round(fx),
                    (int)Math.round(fy),
                    (int)Math.round(fz)
            );

            // --- FLOATING FIX ---
            BlockPos ground = findGround(reader, probe);
            if (ground.getY() < probe.getY())
                probe = new BlockPos(probe.getX(), ground.getY(), probe.getZ());

            // tilt direction based on movement, not angle
            double moveX = fx - prevX;
            double moveZ = fz - prevZ;
            if (Math.abs(moveZ) > Math.abs(moveX))
                tiltDir = moveZ > 0 ? +1 : -1;
            else
                tiltDir = moveX > 0 ? +1 : -1;

            // place root
            place2x2Tilted(reader, replacer, rand, probe, tiltDir, config);

            // small detail
            if (rand.nextFloat() < 0.12f)
                safePlaceLog(reader, replacer, rand,
                        probe.above().offset(-orientX, 0, -orientZ), config);

            prevX = fx;
            prevZ = fz;
        }
    }

    /* ---------------------- Bottom roots (strong downward, reach ground) ---------------------- */
    private void generateBottomRoot(LevelSimulatedReader reader,
                                    BiConsumer<BlockPos, BlockState> replacer,
                                    RandomSource rand,
                                    BlockPos trunkOrigin,
                                    double angle,
                                    int horizSteps,
                                    TreeConfiguration config) {

        double dirX = Math.cos(angle);
        double dirZ = Math.sin(angle);

        // Initial position (slightly above the base so roots visibly connect)
        double fx = trunkOrigin.getX() + dirX * 1.4;
        double fz = trunkOrigin.getZ() + dirZ * 1.4;
        double fy = trunkOrigin.getY() + 3 + rand.nextDouble() * 2; // Y+3..Y+5

        double prevX = fx;
        double prevZ = fz;

    /* ------------------------------
       PHASE 1: Outward gentle curve
       ------------------------------ */
        for (int i = 0; i < horizSteps; i++) {

            prevX = fx;
            prevZ = fz;

            fx += dirX * (0.9 + rand.nextDouble() * 0.3);
            fz += dirZ * (0.9 + rand.nextDouble() * 0.3);

            // slight sag
            fy -= 0.05 + rand.nextDouble() * 0.05;

            BlockPos probe = new BlockPos(
                    (int)Math.round(fx),
                    (int)Math.round(fy),
                    (int)Math.round(fz)
            );

            // tilt direction based on movement, NOT world coords
            double mx = fx - prevX;
            double mz = fz - prevZ;

            int tiltDir = Math.abs(mz) > Math.abs(mx)
                    ? (mz > 0 ? +1 : -1)
                    : (mx > 0 ? +1 : -1);

            place2x2Tilted(reader, replacer, rand, probe, tiltDir, config);
        }

    /* ------------------------------
       PHASE 2: Smooth downward curve
       ------------------------------ */
        int falling = 0;

        while (falling++ < 100) {

            prevX = fx;
            prevZ = fz;

            // natural downward arc (not vertical teleport)
            fy -= 0.45 + rand.nextDouble() * 0.25;
            fx += (rand.nextDouble() - 0.5) * 0.1;
            fz += (rand.nextDouble() - 0.5) * 0.1;

            BlockPos probe = new BlockPos(
                    (int)Math.round(fx),
                    (int)Math.round(fy),
                    (int)Math.round(fz)
            );

            // movement direction (correct tilt)
            double mx = fx - prevX;
            double mz = fz - prevZ;

            int tiltDir = Math.abs(mz) > Math.abs(mx)
                    ? (mz > 0 ? +1 : -1)
                    : (mx > 0 ? +1 : -1);

            if (!reader.isStateAtPosition(probe.below(), s -> s.isAir())) {
                // Block below is solid — root reached ground
                place2x2Tilted(reader, replacer, rand, probe, tiltDir, config);

                if (rand.nextFloat() < 0.2f) {
                    safePlaceLog(reader, replacer, rand, probe.below(), config);
                }
                break;
            }

            // still falling → draw tilted segment
            place2x2Tilted(reader, replacer, rand, probe, tiltDir, config);
        }
    }

    /* ---------------------- Main placement ---------------------- */
    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(LevelSimulatedReader reader,
                                                            BiConsumer<BlockPos, BlockState> replacer,
                                                            RandomSource random,
                                                            int ignoredHeightParam,
                                                            BlockPos featurePos,
                                                            TreeConfiguration config) {

        List<FoliagePlacer.FoliageAttachment> attachments = new ArrayList<>();

        // height 60-90
        final int MIN = 60, MAX = 90;
        int height = MIN + random.nextInt(Math.max(1, MAX - MIN + 1));

        // trunk origin: center the 4x4 trunk on featurePos
        BlockPos trunkOrigin = featurePos.offset(-1, 0, -1);

        // Build trunk layers (4x4 bottom -> 3x3 top) with gentle drift
        double driftX = 0.0, driftZ = 0.0;
        final double jitterPerLayer = 0.12;
        for (int layer = 0; layer < height; ++layer) {
            driftX += (random.nextDouble() - 0.5) * jitterPerLayer;
            driftZ += (random.nextDouble() - 0.5) * jitterPerLayer;
            int offX = Math.round((float) Math.max(-6, Math.min(6, driftX)));
            int offZ = Math.round((float) Math.max(-6, Math.min(6, driftZ)));

            BlockPos layerCenter = trunkOrigin.offset(offX, layer, offZ);

            int size = (layer < Math.round(height * 0.60f)) ? 6 : 4;
            int radius = size / 2;

            for (int dx = -radius; dx <= radius; dx++) {
                for (int dz = -radius; dz <= radius; dz++) {
                    if (Math.abs(dx) == radius && Math.abs(dz) == radius && random.nextFloat() < 0.16f) continue;

                    BlockPos place = layerCenter.offset(dx, 0, dz);
                    if (layer == 0) place = findGround(reader, place.above(6)).above(0);

                    // Place main trunk log
                    safePlaceLog(reader, replacer, random, place, config);

                    // --------------------- SUPPORT FIX ---------------------
                    // Automatically fill floating gaps in the lower trunk
                    if (layer < 6) {
                        BlockPos under = place.below();
                        if (reader.isStateAtPosition(under, s ->
                                s.isAir() || s.canBeReplaced() || s.is(TheEndOfLimboTags.Blocks.REPLACEABLE_PLANTLIKE))) {
                            safePlaceLog(reader, replacer, random, under, config);
                        }
                    }
                    // --------------------------------------------------------
                }
            }

            // small lateral details near base
            if (layer < 8 && random.nextFloat() < 0.22f) {
                BlockPos side = layerCenter.offset(-2 + random.nextInt(5), 0, -2 + random.nextInt(5));
                BlockPos g = findGround(reader, side.above(6));
                safePlaceLog(reader, replacer, random, g, config);
            }
        }

        // ----------------- Top roots (parabolic, 2x2, 7..12) -----------------
        int topRootCount = 7 + random.nextInt(6); // 7..12
        for (int i = 0; i < topRootCount; i++) {
            double angle = (Math.PI * 2.0 * i) / (double) topRootCount + (random.nextDouble() - 0.5) * 0.3;
            int maxLen = 6 + random.nextInt(6); // outward march length
            generateTopRoot(reader, replacer, random, trunkOrigin, angle, maxLen, height, config);
        }

        // ----------------- Bottom roots (strong downward, 8..13) -----------------
        int bottomRootCount = 8 + random.nextInt(6); // 8..13
        for (int i = 0; i < bottomRootCount; i++) {
            double angle = (Math.PI * 2.0 * i) / (double) bottomRootCount + (random.nextDouble() - 0.5) * 0.3;
            int horiz = 3 + random.nextInt(3);
            generateBottomRoot(reader, replacer, random, trunkOrigin, angle, horiz, config);
        }

        // small extras for organic clutter
        int extras = 1 + random.nextInt(3);
        for (int e = 0; e < extras; e++) {
            double angle = random.nextDouble() * Math.PI * 2.0;
            generateTopRoot(reader, replacer, random, trunkOrigin, angle, 3 + random.nextInt(4), height, config);
            generateBottomRoot(reader, replacer, random, trunkOrigin, angle, 2 + random.nextInt(3), config);
        }

        BlockPos trunkTopCenter = trunkOrigin.above(height - 1);

// radius = how far the ring is from the trunk center
        for (int ringLayer = 0; ringLayer < 5; ringLayer++) {

            // Y level of this layer of rings
            BlockPos ringCenter = trunkTopCenter.above(-ringLayer);

            // Number of rings at this height (more at top, fewer lower)
            int ringsThisLayer = 6 - ringLayer;  // 6,5,4,3,2...

            for (int i = 0; i < ringsThisLayer; i++) {

                // Radius shrinks as we go down
                int radiusBase = 10 - ringLayer * 2;   // 10,8,6,4,2
                int ringRadius = radiusBase - i + random.nextInt(2);

                if (ringRadius < 2) continue;

                generateStemRing(reader, replacer, random, ringCenter, ringRadius, height, config);

                attachments.add(new FoliagePlacer.FoliageAttachment(
                        ringCenter,
                        ringRadius,
                        false
                ));
            }
        }

        return attachments;
    }
}
