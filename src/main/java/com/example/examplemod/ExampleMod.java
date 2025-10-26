package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.custom.ModFlammableRotatedPillarBlock;
import com.example.examplemod.block.entity.ModBlockEntities;
import com.example.examplemod.effect.ModEffects;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.entity.client.ModBoatRenderer;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.item.custom.BlackSwordOfDeath;
import com.example.examplemod.item.custom.WhiteSwordOfJudgement;
import com.example.examplemod.loot.ModLootModifiers;
import com.example.examplemod.network.PlayDementiaMusicPacket;
import com.example.examplemod.recipe.ModRecipes;
import com.example.examplemod.screen.ModMenuTypes;
import com.example.examplemod.screen.MysteryStandScreen;
import com.example.examplemod.sound.ModSounds;
import com.example.examplemod.util.ModWoodTypes;
import com.example.examplemod.villager.ModVillagers;
import com.mojang.logging.LogUtils;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.EntityRenderers;
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

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    public static final Logger LOGGER = LogUtils.getLogger();

    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(MOD_ID, "main"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    // Very Important Comment
    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModeTab.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        ModLootModifiers.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModSounds.register(modEventBus);
        ModEntities.register(modEventBus);
        ModEffects.register(modEventBus);

        ModBlockEntities.register(modEventBus);
        ModMenuTypes.register(modEventBus);

        ModRecipes.register(modEventBus);

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

        LOGGER.info("[ExampleMod] Registered network packets.");
    }

    private void commonSetup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ((FlowerPotBlock) Blocks.FLOWER_POT).addPlant(ModBlocks.DECAYING_HARMONY.getId(), ModBlocks.POTTED_DECAYING_HARMONY);
            ModFlammableRotatedPillarBlock.registerStrippables();
        });

    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            Sheets.addWoodType(ModWoodTypes.ABYSS);

            EntityRenderers.register(ModEntities.MOD_BOAT.get(), pContext -> new ModBoatRenderer(pContext, false));
            EntityRenderers.register(ModEntities.MOD_CHEST_BOAT.get(), pContext -> new ModBoatRenderer(pContext, true));

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_BLACK_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_WHITE_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModBlocks.DECAYING_HARMONY.get(), RenderType.cutout());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.POTTED_DECAYING_HARMONY.get(), RenderType.cutout());

            MenuScreens.register(ModMenuTypes.MYSTERY_STAND_MENU.get(), MysteryStandScreen::new);
        }
    }
}