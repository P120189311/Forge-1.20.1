package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.custom.*;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ExampleMod.MOD_ID);

    //Materials
    public static final RegistryObject<Item> BLACKY = ITEMS.register("blacky",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));
    public static final RegistryObject<Item> WHITY = ITEMS.register("whity",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));

    //Misc Tools
    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().durability(32)));

    //Foods
    public static final RegistryObject<Item> VOID_OF_KNOWLEDGE = ITEMS.register("void_of_knowledge",
            () -> new Item(new Item.Properties().fireResistant().food(ModFoods.VOID_OF_KNOWLEDGE)));

    //Burnables
    public static final RegistryObject<Item> ETERNAL_FLAME = ITEMS.register("eternal_flame",
            () -> new EternalFlameItem(new Item.Properties().fireResistant()));

    //Black Tools
    public static final RegistryObject<Item> ABSOLUTE_BLACK_SWORD = ITEMS.register("absolute_black_sword",
            () -> new BlackSwordOfDeath(ModTiers.ABSOLUTE_BLACK, 0, -2.4f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_PICKAXE = ITEMS.register("absolute_black_pickaxe",
            () -> new PickaxeItem(ModTiers.ABSOLUTE_BLACK, 1, -2.8f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_SHOVEL = ITEMS.register("absolute_black_shovel",
            () -> new ShovelItem(ModTiers.ABSOLUTE_BLACK, 2, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_AXE = ITEMS.register("absolute_black_axe",
            () -> new AxeItem(ModTiers.ABSOLUTE_BLACK, 6, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_HOE = ITEMS.register("absolute_black_hoe",
            () -> new HoeItem(ModTiers.ABSOLUTE_BLACK, -2, 6f,
                    new  Item.Properties().fireResistant()));

    //White Tools
    public static final RegistryObject<Item> ABSOLUTE_WHITE_SWORD = ITEMS.register("absolute_white_sword",
            () -> new WhiteSwordOfJudgement(ModTiers.ABSOLUTE_WHITE, 0, -2.4f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_PICKAXE = ITEMS.register("absolute_white_pickaxe",
            () -> new PickaxeItem(ModTiers.ABSOLUTE_WHITE, 1, -2.8f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_SHOVEL = ITEMS.register("absolute_white_shovel",
            () -> new ShovelItem(ModTiers.ABSOLUTE_WHITE, 2, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_AXE = ITEMS.register("absolute_white_axe",
            () -> new AxeItem(ModTiers.ABSOLUTE_WHITE, 6, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_HOE = ITEMS.register("absolute_white_hoe",
            () -> new HoeItem(ModTiers.ABSOLUTE_WHITE, -2, 1f,
                    new  Item.Properties().fireResistant()));

    //Black Armor
    public static final RegistryObject<Item> ABSOLUTE_BLACK_HELMET = ITEMS.register("absolute_black_helmet",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_BLACK, EquipmentSlot.HEAD,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_CHESTPLATE = ITEMS.register("absolute_black_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_BLACK, EquipmentSlot.CHEST,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_LEGGINGS = ITEMS.register("absolute_black_leggings",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_BLACK, EquipmentSlot.LEGS,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_BOOTS = ITEMS.register("absolute_black_boots",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_BLACK, EquipmentSlot.FEET,
                    new  Item.Properties().fireResistant()));

    //White Armor
    public static final RegistryObject<Item> ABSOLUTE_WHITE_HELMET = ITEMS.register("absolute_white_helmet",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_WHITE, EquipmentSlot.HEAD,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_CHESTPLATE = ITEMS.register("absolute_white_chestplate",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_WHITE, EquipmentSlot.CHEST,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_LEGGINGS = ITEMS.register("absolute_white_leggings",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_WHITE, EquipmentSlot.LEGS,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_BOOTS = ITEMS.register("absolute_white_boots",
            () -> new ArmorItem(ModArmorMaterials.ABSOLUTE_WHITE, EquipmentSlot.FEET,
                    new  Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
