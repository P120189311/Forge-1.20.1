package com.p120189311.theendoflimbo.block.custom;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ToolAction;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;

public class AbyssFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public AbyssFlammableRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 0;
    }

    private static final Map<Block, Block> STRIPPABLES = new HashMap<>();

    public static void registerStrippables() {
        STRIPPABLES.put(TheEndOfLimboBlocks.ABYSS_LOG.get(), TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get());
        STRIPPABLES.put(TheEndOfLimboBlocks.ABYSS_WOOD.get(), TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get());
    }

    @Override
    public @Nullable BlockState getToolModifiedState(BlockState state, UseOnContext context, ToolAction toolAction, boolean simulate) {
        return getAxeStrippedState(state, context);
    }

    @Nullable
    private static BlockState getAxeStrippedState(BlockState state, UseOnContext context) {
        if (context.getItemInHand().getItem() instanceof AxeItem) {
            Block stripped = STRIPPABLES.get(state.getBlock());
            if (stripped != null) {
                return stripped.defaultBlockState().setValue(AXIS, state.getValue(AXIS));
            }
        }
        return null;
    }
}
