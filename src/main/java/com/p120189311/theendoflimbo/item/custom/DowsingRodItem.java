package com.p120189311.theendoflimbo.item.custom;

import com.p120189311.theendoflimbo.util.TheEndOfLimboTags;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.Nullable;
import net.minecraft.core.registries.BuiltInRegistries;

import java.util.List;

public class DowsingRodItem extends Item {
    public DowsingRodItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if (pContext.getLevel().isClientSide()) {
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for (int i = 0; i <= positionClicked.getY() + 64; i++) {
                Block blockBelow = pContext.getLevel().getBlockState(positionClicked.below(i)).getBlock();

                if (isValuableBlock(blockBelow)) {
                    outputValuableCoordinates(positionClicked.below(i), player, blockBelow);
                    foundBlock = true;
                    break;
                }
            }

            if (!foundBlock) {
                player.sendSystemMessage(Component.translatable("item.theendoflimbo.dowsing_rod.no_valuables"));
            }
        }

        pContext.getItemInHand().hurtAndBreak(1, pContext.getPlayer(),
                (player) -> player.broadcastBreakEvent(player.getUsedItemHand()));

        return super.useOn(pContext);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        if (Screen.hasShiftDown()) {
            pTooltipComponents.add(Component.translatable("tooltip.theendoflimbo.dowsing_rod.tooltip.shift"));
        } else {
            pTooltipComponents.add(Component.translatable("tooltip.theendoflimbo.dowsing_rod.tooltip"));
        }
    }

    private void outputValuableCoordinates(BlockPos blockPos, Player player, Block blockBelow) {
        player.sendSystemMessage(Component.literal("Found " + blockBelow.asItem().toString() + " at " +
                "(" + blockPos.getX() + ", " + blockPos.getY() + "," + blockPos.getZ() + ")"));
    }

    private boolean isValuableBlock(Block block) {
        return BuiltInRegistries.BLOCK.getResourceKey(block)
                .flatMap(BuiltInRegistries.BLOCK::getHolder)
                .map(holder -> holder.is(TheEndOfLimboTags.Blocks.DOWSING_ROD_VALUABLES))
                .orElse(false);
    }
}