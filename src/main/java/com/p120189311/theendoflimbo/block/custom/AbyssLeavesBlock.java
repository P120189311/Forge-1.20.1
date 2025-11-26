package com.p120189311.theendoflimbo.block.custom;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class AbyssLeavesBlock extends LeavesBlock {
    public AbyssLeavesBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

        return 1000;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {

        return 400;
    }

    @Override
    public void onCaughtFire(BlockState state, Level level, BlockPos pos, @Nullable Direction direction, @Nullable LivingEntity igniter) {
        super.onCaughtFire(state, level, pos, direction, igniter);

        if (!level.isClientSide) {
            RandomSource random = level.random;

            if (random.nextFloat() < 0.025f) {
                popResource(level, pos, new ItemStack(TheEndOfLimboBlocks.ABYSS_SAPLING.get()));
            }

            if (random.nextFloat() < 0.005f) {
                popResource(level, pos, new ItemStack(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get()));
            }
        }
    }
}
