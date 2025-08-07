package com.example.examplemod;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import com.mojang.logging.LogUtils;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.RenderType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExampleMod.MOD_ID)
public class ExampleMod {
    public static final String MOD_ID = "examplemod";
    private static final Logger LOGGER = LogUtils.getLogger();

    // Very Important Comment
    public ExampleMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    private void addCreative(CreativeModeTabEvent.BuildContents event) {
        if(event.getTab() == ModCreativeModeTab.Black){
            event.accept(ModItems.BLACKY);
            event.accept(ModBlocks.ABSOLUTE_BLACK);
            event.accept(ModBlocks.ABSOLUTE_BLACK_ORE);
            event.accept(ModBlocks.ABSOLUTE_BLACK_STAIRS);
            event.accept(ModBlocks.ABSOLUTE_BLACK_SLAB);
            event.accept(ModBlocks.ABSOLUTE_BLACK_FENCE);
            event.accept(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE);
            event.accept(ModBlocks.ABSOLUTE_BLACK_WALL);
            event.accept(ModBlocks.ABSOLUTE_BLACK_BUTTON);
            event.accept(ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE);
            event.accept(ModBlocks.ABSOLUTE_BLACK_DOOR);
            event.accept(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR);
        }

        if(event.getTab() == ModCreativeModeTab.White){
            event.accept(ModItems.WHITY);
            event.accept(ModBlocks.ABSOLUTE_WHITE);
            event.accept(ModBlocks.ABSOLUTE_WHITE_ORE);
            event.accept(ModBlocks.ABSOLUTE_WHITE_STAIRS);
            event.accept(ModBlocks.ABSOLUTE_WHITE_SLAB);
            event.accept(ModBlocks.ABSOLUTE_WHITE_FENCE);
            event.accept(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE);
            event.accept(ModBlocks.ABSOLUTE_WHITE_WALL);
            event.accept(ModBlocks.ABSOLUTE_WHITE_BUTTON);
            event.accept(ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE);
            event.accept(ModBlocks.ABSOLUTE_WHITE_DOOR);
            event.accept(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR);
        }

        if(event.getTab() == ModCreativeModeTab.Mod_Misc){
            event.accept(ModItems.DOWSING_ROD);
            event.accept(ModItems.ETERNAL_FLAME);
            event.accept(ModBlocks.JUMPY_BLOCK);
        }

        if(event.getTab() == ModCreativeModeTab.Mod_Food){
            event.accept(ModItems.VOID_OF_KNOWLEDGE);
        }
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_BLACK_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_WHITE_DOOR.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), RenderType.translucent());
        }
    }
}