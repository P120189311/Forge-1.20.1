package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.CreativeModeTabEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {
    public static CreativeModeTab Black;

    public static CreativeModeTab White;

    public static CreativeModeTab Mod_Misc;

    public static CreativeModeTab Mod_Food;

    @SubscribeEvent
    public static void registerCreativeModeTabs(CreativeModeTabEvent.Register event) {
        Black = event.registerCreativeModeTab(new ResourceLocation(ExampleMod.MOD_ID, "black"),
                builder -> builder.icon(() -> new ItemStack(ModItems.BLACKY.get()))
                        .title(Component.translatable("itemGroup.black")));

        White = event.registerCreativeModeTab(new ResourceLocation(ExampleMod.MOD_ID, "white"),
                builder -> builder.icon(() -> new ItemStack(ModItems.WHITY.get()))
                        .title(Component.translatable("itemGroup.white")));

        Mod_Misc = event.registerCreativeModeTab(new ResourceLocation(ExampleMod.MOD_ID, "mod_misc"),
                builder -> builder.icon(() -> new ItemStack(ModItems.DOWSING_ROD.get()))
                        .title(Component.translatable("itemGroup.mod_misc")));

        Mod_Food = event.registerCreativeModeTab(new ResourceLocation(ExampleMod.MOD_ID, "mod_food"),
                builder -> builder.icon(() -> new ItemStack(ModItems.VOID_OF_KNOWLEDGE.get()))
                        .title(Component.translatable("itemGroup.mod_food")));
    }

}