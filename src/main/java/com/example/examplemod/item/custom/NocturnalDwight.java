package com.example.examplemod.item.custom;

import com.example.examplemod.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NocturnalDwight extends Item {
    public NocturnalDwight(Properties p_41383_) {
        super(p_41383_);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        BlockPos center = context.getClickedPos();

        boolean anyTransformed = false; // track if at least one block was changed

        if (!level.isClientSide) {
            // Go through a 3x3x3 cube around the clicked position
            for (int dx = -1; dx <= 1; dx++) {
                for (int dy = -1; dy <= 1; dy++) {
                    for (int dz = -1; dz <= 1; dz++) {
                        BlockPos targetPos = center.offset(dx, dy, dz);
                        BlockState state = level.getBlockState(targetPos);

                        boolean blockTransformed = false; // reset for each block

                        if (state.is(Blocks.DIAMOND_ORE) || state.is(Blocks.DEEPSLATE_DIAMOND_ORE)) {
                            level.setBlock(targetPos, ModBlocks.ABSOLUTE_BLACK_ORE.get().defaultBlockState(), 3);
                            blockTransformed = true;
                        } else if (state.is(Blocks.EMERALD_ORE) || state.is(Blocks.DEEPSLATE_EMERALD_ORE)) {
                            level.setBlock(targetPos, ModBlocks.ABSOLUTE_WHITE_ORE.get().defaultBlockState(), 3);
                            blockTransformed = true;
                        } else if (state.is(ModBlocks.ABYSS_PLANKS_CURSED.get())) {
                            level.setBlock(targetPos, ModBlocks.ABYSS_PLANKS.get().defaultBlockState(), 3);
                            blockTransformed = true;
                        }

                        // Only spawn particles if THIS block was transformed
                        if (blockTransformed && level instanceof ServerLevel serverLevel) {
                            serverLevel.sendParticles(ParticleTypes.END_ROD,
                                    targetPos.getX() + 0.5,
                                    targetPos.getY() + 1.0,
                                    targetPos.getZ() + 0.5,
                                    20,
                                    0.5, 0.5, 0.5,
                                    0.01
                            );
                            anyTransformed = true; // mark that at least one block changed
                        }
                    }
                }
            }

            // Play sound ONCE if any block was transformed
            if (anyTransformed) {
                level.playSound(null, center, SoundEvents.BEACON_POWER_SELECT, SoundSource.BLOCKS, 1.0F, 1.0F);

                // Consume item only if transformation happened
                context.getItemInHand().shrink(1);

                return InteractionResult.SUCCESS;
            }
        }

        return InteractionResult.PASS;
    }
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.nocturnal_dwight").withStyle(ChatFormatting.LIGHT_PURPLE).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}
