package com.example.examplemod.worldgen.tree;

import com.example.examplemod.worldgen.ModConfiguredFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.level.SaplingGrowTreeEvent;
import net.minecraftforge.eventbus.api.Event;
import org.jetbrains.annotations.Nullable;

public class AbyssTreeGrower extends AbstractTreeGrower {

    @Nullable
    @Override
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredFeature(RandomSource random, boolean bees) {
        return null;
    }

    @Nullable
    protected ResourceKey<ConfiguredFeature<?, ?>> getConfiguredMegaFeature(RandomSource random) {
        // 3×3 huge tree
        return ModConfiguredFeatures.ABYSS_KEY;
    }

    @Override
    public boolean growTree(ServerLevel level, ChunkGenerator generator, BlockPos pos,
                            BlockState state, RandomSource random) {

        for (int ox = -2; ox <= 0; ox++) {
            for (int oz = -2; oz <= 0; oz++) {
                if (isThreeByThreeSapling(state, level, pos, ox, oz)) {
                    return placeMega(level, generator, pos, state, random, ox, oz);
                }
            }
        }

        return super.growTree(level, generator, pos, state, random);
    }

    public boolean placeMega(ServerLevel level, ChunkGenerator generator, BlockPos pos,
                             BlockState state, RandomSource random, int ox, int oz) {

        ResourceKey<ConfiguredFeature<?, ?>> key = this.getConfiguredMegaFeature(random);
        if (key == null) return false;

        Holder<ConfiguredFeature<?, ?>> holder =
                level.registryAccess().registryOrThrow(Registries.CONFIGURED_FEATURE)
                        .getHolder(key).orElse(null);

        SaplingGrowTreeEvent event =
                ForgeEventFactory.blockGrowFeature(level, random, pos, holder);

        holder = event.getFeature();

        if (event.getResult() == Event.Result.DENY || holder == null)
            return false;

        ConfiguredFeature<?, ?> feature = holder.value();

        for (int dx = 0; dx < 3; dx++) {
            for (int dz = 0; dz < 3; dz++) {
                BlockPos sapPos = pos.offset(ox + dx, 0, oz + dz);
                level.setBlock(sapPos, Blocks.AIR.defaultBlockState(), 4);
            }
        }

        BlockPos origin = pos.offset(ox + 1, 0, oz + 1);

        if (feature.place(level, generator, random, origin)) {
            return true;
        }

        // Failed → put saplings back
        for (int dx = 0; dx < 3; dx++) {
            for (int dz = 0; dz < 3; dz++) {
                BlockPos sapPos = pos.offset(ox + dx, 0, oz + dz);
                level.setBlock(sapPos, state, 4);
            }
        }

        return false;
    }

    public static boolean isThreeByThreeSapling(BlockState state, BlockGetter level,
                                                BlockPos pos, int ox, int oz) {

        Block sapling = state.getBlock();

        for (int dx = 0; dx < 3; dx++) {
            for (int dz = 0; dz < 3; dz++) {
                if (!level.getBlockState(pos.offset(ox + dx, 0, oz + dz)).is(sapling)) {
                    return false;
                }
            }
        }

        return true;
    }
}