package com.p120189311.theendoflimbo.worldgen.tree.custom;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.worldgen.tree.TheEndOfLimboFoliagePlacers;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.*;

public class AbyssFoliagePlacer extends FoliagePlacer {

    public static final Codec<AbyssFoliagePlacer> CODEC =
            RecordCodecBuilder.create(inst ->
                    foliagePlacerParts(inst)
                            .and(IntProvider.CODEC.fieldOf("height").forGetter(fp -> fp.height))
                            .apply(inst, AbyssFoliagePlacer::new));

    private final IntProvider height;
    private final Set<BlockPos> decayLeaves = new HashSet<>();

    public AbyssFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return TheEndOfLimboFoliagePlacers.ABYSS_FOLIAGE_PLACER.get();
    }

    @Override
    protected void createFoliage(
            LevelSimulatedReader level,
            FoliageSetter setter,
            RandomSource random,
            TreeConfiguration config,
            int trunkHeight,
            FoliageAttachment attachment,
            int foliageHeight,
            int foliageRadius,
            int offset
    ) {
        int R = attachment.radiusOffset();
        R = (int)(R * 1.25);
        if (R < 4) R = 4;

        BlockPos domeCenter = attachment.pos().above(4);

        // --------- 1. FILLER RINGS (unchanged, keeps your crown structure) ---------
        for (int dy = 0; dy < 4; dy++) {
            int layerRadius = R - dy;
            if (dy == 3) layerRadius -= 1;
            if (layerRadius >= 2) {
                placeLeafCircle(level, setter, random, config,
                        attachment.pos().below(dy), layerRadius);
            }
        }

        // --------- 2. MAIN DOME + BELL-SHAPED CANOPY ---------

        int domeRadius = R + 2;

        // Push outward to prevent touching trunk
        int pushOut = 3;

        for (int dx = -domeRadius; dx <= domeRadius; dx++) {
            for (int dz = -domeRadius; dz <= domeRadius; dz++) {

                double d = Math.sqrt(dx * dx + dz * dz);
                if (d == 0) d = 0.0001;

                double t = d / domeRadius; // 0 → 1

// ------------ New safe droop system ---------------
                int dropLocal;

// Upper half stays smooth
                if (t < 0.55) {
                    dropLocal = (int)(t * t * 4); // gentle dome
                }
// Middle transitions softly
                else if (t < 0.8) {
                    dropLocal = 2 + (int)((t - 0.55) * 6);
                }
// Outer skirt tight + lifted (prevents holes)
                else {
                    double s = (t - 0.8) / 0.2; // 0 → 1
                    dropLocal = 4 + (int)(s * 2);  // max 6 downward
                }

// ------------ New tighter radius pull -------------
                int pushOutLocal = pushOut;

// shrink radius in last 20% only
                if (t > 0.8) {
                    pushOutLocal -= 1;     // tighten
                    if (pushOutLocal < 0) pushOutLocal = 0;
                }

// ------------ Normal vector -------------
                double nx = dx / d;
                double nz = dz / d;

                int ox = (int)(nx * pushOutLocal);
                int oz = (int)(nz * pushOutLocal);

                BlockPos leafBase = domeCenter.offset(
                        dx + ox,
                        -dropLocal,
                        dz + oz
                );

                // -------- 3. LUSH THICKNESS (4-layer fill) --------
                for (int thick = 0; thick < 4; thick++) {
                    // minor inward & upward filling to close gaps
                    BlockPos p = leafBase.offset(
                            -(int)(nx * thick),
                            thick == 0 ? 0 : 1,
                            -(int)(nz * thick)
                    );

                    tryCustomLeaf(level, setter, random, config, p);
                }
            }
        }
    }


    // --------------------------
    // SUPPORT FUNCTIONS
    // --------------------------

    private void placeLeafCircle(
            LevelSimulatedReader world,
            FoliageSetter setter,
            RandomSource random,
            TreeConfiguration config,
            BlockPos center,
            int radius
    ) {
        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {
                if (x * x + z * z <= radius * radius) {
                    BlockPos leafPos = center.offset(x, 0, z);
                    tryCustomLeaf(world, setter, random, config, leafPos);
                }
            }
        }
    }

    private void tryCustomLeaf(
            LevelSimulatedReader level,
            FoliageSetter setter,
            RandomSource random,
            TreeConfiguration config,
            BlockPos pos
    ) {
        if (FoliagePlacer.tryPlaceLeaf(level, setter, random, config, pos)) {

            BlockState leaf = TheEndOfLimboBlocks.ABYSS_LEAVES.get()
                    .defaultBlockState()
                    .setValue(LeavesBlock.PERSISTENT, true);

            setter.set(pos, leaf);
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int trunkHeight, TreeConfiguration config) {
        return height.sample(random);
    }

    @Override
    protected boolean shouldSkipLocation(
            RandomSource random, int dx, int y, int dz, int radius, boolean giantTrunk
    ) {
        return dx * dx + dz * dz > radius * radius;
    }
}