package com.p120189311.theendoflimbo.event;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.entity.TheEndOfLimboBlockEntities;
import com.p120189311.theendoflimbo.block.entity.renderer.MysteryStandBlockEntityRenderer;
import com.p120189311.theendoflimbo.entity.client.TheEndOfLimboModelLayers;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.renderer.blockentity.HangingSignRenderer;
import net.minecraft.client.renderer.blockentity.SignRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TheEndOfLimboMod.MAIN, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TheEndOfLimboEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(TheEndOfLimboModelLayers.ABYSS_BOAT_LAYERS, BoatModel::createBodyModel);
        event.registerLayerDefinition(TheEndOfLimboModelLayers.ABYSS_CHEST_BOAT_LAYERS, ChestBoatModel::createBodyModel);
    }

    @SubscribeEvent
    public static void registerBER(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(TheEndOfLimboBlockEntities.MYSTERY_STAND_BE.get(), MysteryStandBlockEntityRenderer::new);

        event.registerBlockEntityRenderer(TheEndOfLimboBlockEntities.MOD_SIGN.get(), SignRenderer::new);
        event.registerBlockEntityRenderer(TheEndOfLimboBlockEntities.MOD_HANGING_SIGN.get(), HangingSignRenderer::new);
    }
}
