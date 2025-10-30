package com.example.examplemod.item.custom;

import com.example.examplemod.block.ModBlocks;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class InfectiousBlackPickaxe extends PickaxeItem {

    private static final String READY_TAG = "Infection_ready";
    private static final int MAX_DISTANCE = 3;
    private static final int SPREAD_INTERVAL = 40;

    public InfectiousBlackPickaxe(Tier p_42961_, int p_42962_, float p_42963_, Properties p_42964_) {
        super(p_42961_, p_42962_, p_42963_, p_42964_);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);

        if (!level.isClientSide) {
            // Toggle readiness
            boolean ready = stack.getOrCreateTag().getBoolean(READY_TAG);
            stack.getOrCreateTag().putBoolean(READY_TAG, !ready);
            player.displayClientMessage(
                    net.minecraft.network.chat.Component.literal("Infectious Black Pickaxe " + (!ready ? "READY" : "DISABLED")),
                    true
            );
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide);
    }

    @Override
    public boolean mineBlock(ItemStack stack, Level level, BlockState state, BlockPos pos, LivingEntity entity) {
        if (level.isClientSide) return super.mineBlock(stack, level, state, pos, entity);

        if (entity instanceof Player player) {
            ServerLevel serverLevel = (ServerLevel) level;

            // Only spread if armed & breaking iron ore
            if (stack.getOrCreateTag().getBoolean(READY_TAG) && state.is(Blocks.IRON_ORE)) {
                Block offhandBlock = getOffhandBlock(player);
                if (offhandBlock == null) return super.mineBlock(stack, level, state, pos, entity);

                // Replace the center block
                serverLevel.setBlock(pos, offhandBlock.defaultBlockState(), 3);

                // Start spreading
                startInfection(serverLevel, pos, offhandBlock);

                // Put on cooldown
                player.getCooldowns().addCooldown(this, 20 * 6);
                stack.getOrCreateTag().putBoolean(READY_TAG, false); // reset
            }
        }

        return super.mineBlock(stack, level, state, pos, entity);
    }

    private Block getOffhandBlock(Player player) {
        ItemStack offhand = player.getOffhandItem();
        if (offhand.is(ModBlocks.ABSOLUTE_BLACK_ORE.get().asItem())) {
            return ModBlocks.ABSOLUTE_BLACK_ORE.get();
        }
        if (offhand.is(ModBlocks.ABSOLUTE_WHITE_ORE.get().asItem())) {
            return ModBlocks.ABSOLUTE_WHITE_ORE.get();
        }
        return null;
    }

    private static class Tendril {
        BlockPos currentPos;
        Direction direction;
        int length;
        int maxLength;

        Tendril(BlockPos start, Direction dir, int maxLength) {
            this.currentPos = start;
            this.direction = dir;
            this.length = 0;
            this.maxLength = maxLength;
        }
    }

    private void startInfection(ServerLevel level, BlockPos origin, Block targetBlock) {
        Set<BlockPos> visited = new HashSet<>();
        Queue<InfectedNode> queue = new LinkedList<>();

        // Infect origin
        infectBlock(level, origin, targetBlock, visited);
        queue.add(new InfectedNode(origin, 0));

        while (!queue.isEmpty()) {
            InfectedNode node = queue.poll();
            if (node.distance >= MAX_DISTANCE) continue;

            for (Direction dir : Direction.values()) {
                BlockPos next = node.pos.relative(dir);

                if (!visited.contains(next) && level.getBlockState(next).is(Blocks.IRON_ORE)) {
                    // Always infect immediate neighbors of the origin
                    if (node.distance == 0 || level.random.nextFloat() < 0.7f) {
                        infectBlock(level, next, targetBlock, visited);
                        queue.add(new InfectedNode(next, node.distance + 1));
                    }
                }
            }
        }
    }

    private void infectBlock(ServerLevel level, BlockPos pos, Block targetBlock, Set<BlockPos> visited) {
        visited.add(pos);
        level.setBlock(pos, targetBlock.defaultBlockState(), 3);
    }

    private static class InfectedNode {
        BlockPos pos;
        int distance;

        InfectedNode(BlockPos pos, int distance) {
            this.pos = pos;
            this.distance = distance;
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        tooltip.add(Component.translatable("tooltip.examplemod.infectious_black_pickaxe").withStyle(ChatFormatting.DARK_GRAY).withStyle(ChatFormatting.ITALIC).withStyle(ChatFormatting.BOLD));
    }
}
