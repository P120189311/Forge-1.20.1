package com.p120189311.theendoflimbo.block.custom;

import com.p120189311.theendoflimbo.worldgen.dimension.AbyssDimension;
import com.p120189311.theendoflimbo.worldgen.portal.TheEndOfLimboTeleporter;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class AbyssPortalBlock extends Block {
    public AbyssPortalBlock(Properties p_55926_) {
        super(p_55926_);
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (pPlayer.canChangeDimensions()) {
            handleLimboPortal(pPlayer, pPos);
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.CONSUME;
        }
    }

    private void handleLimboPortal(Entity player, BlockPos pPos) {
        if (player.level() instanceof ServerLevel serverlevel) {
            MinecraftServer minecraftserver = serverlevel.getServer();
            ResourceKey<Level> resourcekey = player.level().dimension() == AbyssDimension.ABYSS_DIMENSION_LEVEL_KEY ?
                    Level.OVERWORLD : AbyssDimension.ABYSS_DIMENSION_LEVEL_KEY;

            ServerLevel portalDimension = minecraftserver.getLevel(resourcekey);
            if (portalDimension != null && !player.isPassenger()) {
                if(resourcekey == AbyssDimension.ABYSS_DIMENSION_LEVEL_KEY) {
                    player.changeDimension(portalDimension, new TheEndOfLimboTeleporter(pPos, true));
                } else {
                    player.changeDimension(portalDimension, new TheEndOfLimboTeleporter(pPos, false));
                }
            }
        }
    }
}
