package com.p120189311.theendoflimbo.item;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.entity.custom.AbyssBoatEntity;
import com.p120189311.theendoflimbo.fluid.TheEndOfLimboFluids;
import com.p120189311.theendoflimbo.item.custom.*;
import com.p120189311.theendoflimbo.sound.TheEndOfLimboSounds;
import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, TheEndOfLimboMod.MAIN);

    // Materials
    public static final RegistryObject<Item> BLACKY = ITEMS.register("blacky",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));
    public static final RegistryObject<Item> WHITY = ITEMS.register("whity",
            () -> new Item(new Item.Properties().fireResistant().stacksTo(32)));

    // Wood
    public static final RegistryObject<Item> ABYSS_SIGN = ITEMS.register("abyss_sign",
            () -> new SignItem(new Item.Properties().stacksTo(16), TheEndOfLimboBlocks.ABYSS_SIGN.get(), TheEndOfLimboBlocks.ABYSS_WALL_SIGN.get()));
    public static final RegistryObject<Item> ABYSS_HANGING_SIGN = ITEMS.register("abyss_hanging_sign",
            () -> new HangingSignItem(TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get(), TheEndOfLimboBlocks.ABYSS_WALL_HANGING_SIGN.get(), new Item.Properties().stacksTo(16)));
    public static final RegistryObject<Item> ABYSS_BOAT = ITEMS.register("abyss_boat",
            () -> new TheEndOfLimboBoatItem(false, AbyssBoatEntity.Type.ABYSS, new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABYSS_CHEST_BOAT = ITEMS.register("abyss_chest_boat",
            () -> new TheEndOfLimboBoatItem(true, AbyssBoatEntity.Type.ABYSS, new Item.Properties().fireResistant()));

    // Fluids
    public static final RegistryObject<Item> SUFFOCATING_WATER_BUCKET = ITEMS.register("suffocating_water_bucket",
            () -> new BucketItem(TheEndOfLimboFluids.SOURCE_SUFFOCATING_WATER, new Item.Properties().craftRemainder(Items.BUCKET).stacksTo(1)));

    // Misc Tools
    public static final RegistryObject<Item> DOWSING_ROD = ITEMS.register("dowsing_rod",
            () -> new DowsingRodItem(new Item.Properties().durability(32)));

    // Seeds
    public static final RegistryObject<Item> BLUE_HILL_TOMATO_SEEDS = ITEMS.register("blue_hill_tomato_seeds",
            () -> new ItemNameBlockItem(TheEndOfLimboBlocks.BLUE_HILL_TOMATO_CROP.get(), new Item.Properties()));

    // Foods
    public static final RegistryObject<Item> VOID_OF_KNOWLEDGE = ITEMS.register("void_of_knowledge",
            () -> new Item(new Item.Properties().fireResistant().food(TheEndOfLimboFoods.VOID_OF_KNOWLEDGE)));
    public static final RegistryObject<Item> BLUE_HILL_TOMATO = ITEMS.register("blue_hill_tomato",
            () -> new BlueHillTomato(new Item.Properties().food(TheEndOfLimboFoods.BLUE_HILL_TOMATO)));

    // Burnables
    public static final RegistryObject<Item> ETERNAL_FLAME = ITEMS.register("eternal_flame",
            () -> new EternalFlameItem(new Item.Properties().fireResistant()));

    // Music Discs
    public static final RegistryObject<Item> AMBULANCE_BEAT_MUSIC_DISC = ITEMS.register("ambulance_beat_music_disc",
            () -> new CursedDisc(7, TheEndOfLimboSounds.AMBULANCE_BEAT.get(), new Item.Properties().stacksTo(1), 1780));
    public static final RegistryObject<Item> BAFANGLAICAI_MUSIC_DISC = ITEMS.register("bafanglaicai_music_disc",
            () -> new RecordItem(7, TheEndOfLimboSounds.BAFANGLAICAI, new Item.Properties().stacksTo(1), 3460));
    public static final RegistryObject<Item> DROP_IT_LIKE_ITS_HOT_MUSIC_DISC = ITEMS.register("drop_it_like_its_hot_music_disc",
            () -> new RecordItem(7, TheEndOfLimboSounds.DROP_IT_LIKE_ITS_HOT, new Item.Properties().stacksTo(1), 2680));

    // Misc Items
    public static final RegistryObject<Item> NOCTURNAL_DWIGHT = ITEMS.register("nocturnal_dwight",
            () -> new NocturnalDwight(new Item.Properties().fireResistant()));
    public static final RegistryObject<Item> TRAPPED_GAMMA_RAY = ITEMS.register("trapped_gamma_ray",
            () -> new TrappedGammaRay(new Item.Properties().fireResistant()));

    // Black Tools
    public static final RegistryObject<Item> ABSOLUTE_BLACK_SWORD = ITEMS.register("absolute_black_sword",
            () -> new BlackSwordOfDeath(TheEndOfLimboTiers.ABSOLUTE_BLACK, 0, -2.4f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_PICKAXE = ITEMS.register("absolute_black_pickaxe",
            () -> new InfectiousBlackPickaxe(TheEndOfLimboTiers.ABSOLUTE_BLACK, 1, -2.8f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_SHOVEL = ITEMS.register("absolute_black_shovel",
            () -> new ShovelItem(TheEndOfLimboTiers.ABSOLUTE_BLACK, 2, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_AXE = ITEMS.register("absolute_black_axe",
            () -> new AxeItem(TheEndOfLimboTiers.ABSOLUTE_BLACK, 6, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_HOE = ITEMS.register("absolute_black_hoe",
            () -> new HoeItem(TheEndOfLimboTiers.ABSOLUTE_BLACK, -2, 6f,
                    new  Item.Properties().fireResistant()));

    // White Tools
    public static final RegistryObject<Item> ABSOLUTE_WHITE_SWORD = ITEMS.register("absolute_white_sword",
            () -> new WhiteSwordOfJudgement(TheEndOfLimboTiers.ABSOLUTE_WHITE, 0, -2.4f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_PICKAXE = ITEMS.register("absolute_white_pickaxe",
            () -> new PickaxeItem(TheEndOfLimboTiers.ABSOLUTE_WHITE, 1, -2.8f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_SHOVEL = ITEMS.register("absolute_white_shovel",
            () -> new ShovelItem(TheEndOfLimboTiers.ABSOLUTE_WHITE, 2, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_AXE = ITEMS.register("absolute_white_axe",
            () -> new AxeItem(TheEndOfLimboTiers.ABSOLUTE_WHITE, 6, -3f,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_HOE = ITEMS.register("absolute_white_hoe",
            () -> new HoeItem(TheEndOfLimboTiers.ABSOLUTE_WHITE, -2, 1f,
                    new  Item.Properties().fireResistant()));

    // Black Armor
    public static final RegistryObject<Item> ABSOLUTE_BLACK_HELMET = ITEMS.register("absolute_black_helmet",
            () -> new BlackHoleArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_BLACK, ArmorItem.Type.HELMET,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_CHESTPLATE = ITEMS.register("absolute_black_chestplate",
            () -> new BlackHoleArmor(TheEndOfLimboArmorMaterials.ABSOLUTE_BLACK, ArmorItem.Type.CHESTPLATE,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_LEGGINGS = ITEMS.register("absolute_black_leggings",
            () -> new BlackHoleArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_BLACK, ArmorItem.Type.LEGGINGS,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_BLACK_BOOTS = ITEMS.register("absolute_black_boots",
            () -> new BlackHoleArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_BLACK, ArmorItem.Type.BOOTS,
                    new  Item.Properties().fireResistant()));

    // White Armor
    public static final RegistryObject<Item> ABSOLUTE_WHITE_HELMET = ITEMS.register("absolute_white_helmet",
            () -> new PurificatingWhiteArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_WHITE, ArmorItem.Type.HELMET,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_CHESTPLATE = ITEMS.register("absolute_white_chestplate",
            () -> new PurificatingWhiteArmor(TheEndOfLimboArmorMaterials.ABSOLUTE_WHITE, ArmorItem.Type.CHESTPLATE,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_LEGGINGS = ITEMS.register("absolute_white_leggings",
            () -> new PurificatingWhiteArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_WHITE, ArmorItem.Type.LEGGINGS,
                    new  Item.Properties().fireResistant()));
    public static final RegistryObject<Item> ABSOLUTE_WHITE_BOOTS = ITEMS.register("absolute_white_boots",
            () -> new PurificatingWhiteArmorText(TheEndOfLimboArmorMaterials.ABSOLUTE_WHITE, ArmorItem.Type.BOOTS,
                    new  Item.Properties().fireResistant()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
