package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.custom.DowsingRodItem;
import com.example.examplemod.item.custom.EternalFlameItem;
import com.example.examplemod.item.custom.JumpyBlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    public static final RegistryObject<Item> BLACKY = ITEMS.register("blacky",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));

    public static final RegistryObject<Item> WHITY = ITEMS.register("whity",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));

    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().durability(32)));

    public static final RegistryObject<Item> VOID_OF_KNOWLEDGE = ITEMS.register("void_of_knowledge",
            () -> new Item(new Item.Properties().fireResistant().food(ModFoods.VOID_OF_KNOWLEDGE)));

    public static final RegistryObject<Item> ETERNAL_FLAME = ITEMS.register("eternal_flame",
            () -> new EternalFlameItem(new Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
