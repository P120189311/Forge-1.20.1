package com.p120189311.theendoflimbo;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.block.custom.AbyssFlammableRotatedPillarBlock;
import com.p120189311.theendoflimbo.block.entity.TheEndOfLimboBlockEntities;
import com.p120189311.theendoflimbo.effect.TheEndOfLimboEffects;
import com.p120189311.theendoflimbo.entity.TheEndOfLimboEntities;
import com.p120189311.theendoflimbo.entity.client.TheEndOfLimboBoatRenderer;
import com.p120189311.theendoflimbo.event.TheEndOfLimboDispenseBehaviors;
import com.p120189311.theendoflimbo.item.TheEndOfLimboCreativeModeTab;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.item.custom.BlackSwordOfDeath;
import com.p120189311.theendoflimbo.item.custom.WhiteSwordOfJudgement;
import com.p120189311.theendoflimbo.loot.TheEndOfLimboLootModifiers;
import com.p120189311.theendoflimbo.network.PlayDementiaMusicPacket;
import com.p120189311.theendoflimbo.recipe.TheEndOfLimboRecipes;
import com.p120189311.theendoflimbo.screen.TheEndOfLimboMenuTypes;
import com.p120189311.theendoflimbo.screen.MysteryStandScreen;
import com.p120189311.theendoflimbo.sound.TheEndOfLimboSounds;
import com.p120189311.theendoflimbo.util.TheEndOfLimboWoodTypes;
import com.p120189311.theendoflimbo.villager.TheEndOfLimboVillagers;
import com.p120189311.theendoflimbo.worldgen.biome.TheEndOfLimboTerrablender;
import com.p120189311.theendoflimbo.worldgen.biome.surface.TheEndOfLimboSurfaceRules;
import com.p120189311.theendoflimbo.worldgen.tree.TheEndOfLimboFoliagePlacers;
import com.p120189311.theendoflimbo.worldgen.tree.TheEndOfLimboTrunkPlacerTypes;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import org.slf4j.Logger;
import terrablender.api.SurfaceRuleManager;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(TheEndOfLimboMod.MAIN)
public class TheEndOfLimboMod {
    public static final String MAIN = "theendoflimbo";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MAIN, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    // Very Important Comment
    public TheEndOfLimboMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        TheEndOfLimboCreativeModeTab.register(modEventBus);
        TheEndOfLimboItems.register(modEventBus);
        TheEndOfLimboBlocks.register(modEventBus);

        TheEndOfLimboLootModifiers.register(modEventBus);
        TheEndOfLimboVillagers.register(modEventBus);

        TheEndOfLimboSounds.register(modEventBus);
        TheEndOfLimboEntities.register(modEventBus);
        TheEndOfLimboEffects.register(modEventBus);

        TheEndOfLimboBlockEntities.register(modEventBus);
        TheEndOfLimboMenuTypes.register(modEventBus);

        TheEndOfLimboRecipes.register(modEventBus);
        TheEndOfLimboTrunkPlacerTypes.register(modEventBus);
        TheEndOfLimboFoliagePlacers.register(modEventBus);

        TheEndOfLimboTerrablender.registerBiomes();

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        MinecraftForge.EVENT_BUS.addListener(BlackSwordOfDeath::onServerTick);
        MinecraftForge.EVENT_BUS.addListener(WhiteSwordOfJudgement::onServerTick);

        registerPackets();
    }

    public static void registerPackets() {
        int id = 0;

        CHANNEL.messageBuilder(PlayDementiaMusicPacket.class, id++)
                .encoder(PlayDementiaMusicPacket::encode)
                .decoder(PlayDementiaMusicPacket::decode)
                .consumerMainThread(PlayDementiaMusicPacket::handle)
                .add();

        LOGGER.info("[TheEndOfLimbo] Registered network packets.");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(TheEndOfLimboBlocks.DECAYING_HARMONY.getId(), TheEndOfLimboBlocks.POTTED_DECAYING_HARMONY);
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(TheEndOfLimboBlocks.ABYSS_SAPLING.getId(), TheEndOfLimboBlocks.POTTED_ABYSS_SAPLING);
            AbyssFlammableRotatedPillarBlock.registerStrippables();
            event.enqueueWork(TheEndOfLimboDispenseBehaviors::register);
            SurfaceRuleManager.addSurfaceRules(SurfaceRuleManager.RuleCategory.OVERWORLD, MAIN, TheEndOfLimboSurfaceRules.makeRules());
        });

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MAIN, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(TheEndOfLimboWoodTypes.ABYSS);

            EntityRenderers.register(TheEndOfLimboEntities.MOD_BOAT.get(), pContext -> new TheEndOfLimboBoatRenderer(pContext, false));
            EntityRenderers.register(TheEndOfLimboEntities.MOD_CHEST_BOAT.get(), pContext -> new TheEndOfLimboBoatRenderer(pContext, true));
            EntityRenderers.register(TheEndOfLimboEntities.TRAPPED_GAMMA_RAY.get(), ThrownItemRenderer::new);

            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.DECAYING_HARMONY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.POTTED_DECAYING_HARMONY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(TheEndOfLimboBlocks.POTTED_ABYSS_SAPLING.get(), RenderType.cutout());

            MenuScreens.register(TheEndOfLimboMenuTypes.MYSTERY_STAND_MENU.get(), MysteryStandScreen::new);
        }
    }
}