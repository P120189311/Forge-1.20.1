package com.example.examplemod.block.custom;

import com.example.examplemod.block.ModBlocks;
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

public class ModFlammableRotatedPillarBlock extends RotatedPillarBlock {
    public ModFlammableRotatedPillarBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 5;
    }

    private static final Map<Block, Block> STRIPPABLES = new HashMap<>();

    /**
     * Called from your mod setup to register strippable pairs safely.
     */
    public static void registerStrippables() {
        STRIPPABLES.put(ModBlocks.ABYSS_LOG.get(), ModBlocks.STRIPPED_ABYSS_LOG.get());
        STRIPPABLES.put(ModBlocks.ABYSS_WOOD.get(), ModBlocks.STRIPPED_ABYSS_WOOD.get());
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
