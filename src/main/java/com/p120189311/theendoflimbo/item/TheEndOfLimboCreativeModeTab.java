package com.p120189311.theendoflimbo.item;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = TheEndOfLimboMod.MAIN, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TheEndOfLimboCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<CreativeModeTab> BLACK = CREATIVE_MODE_TABS.register("black",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.BLACKY.get()))
                    .title(Component.translatable("creativeModeTab.black"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.BLACKY.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> WHITE = CREATIVE_MODE_TABS.register("white",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.WHITY.get()))
                    .title(Component.translatable("creativeModeTab.white"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.WHITY.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_MISC = CREATIVE_MODE_TABS.register("mod_misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.ETERNAL_FLAME.get()))
                    .title(Component.translatable("creativeModeTab.mod_misc"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.DOWSING_ROD.get());
                        pOutput.accept(TheEndOfLimboItems.ETERNAL_FLAME.get());
                        pOutput.accept(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get());
                        pOutput.accept(TheEndOfLimboItems.TRAPPED_GAMMA_RAY.get());
                        pOutput.accept(TheEndOfLimboItems.SUFFOCATING_WATER_BUCKET.get());
                        pOutput.accept(TheEndOfLimboBlocks.JUMPY_BLOCK.get());
                        pOutput.accept(TheEndOfLimboBlocks.DECAYING_HARMONY.get());
                        pOutput.accept(TheEndOfLimboBlocks.MYSTERY_STAND.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_PORTAL.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_FOOD = CREATIVE_MODE_TABS.register("mod_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.VOID_OF_KNOWLEDGE.get()))
                    .title(Component.translatable("creativeModeTab.mod_food"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.VOID_OF_KNOWLEDGE.get());
                        pOutput.accept(TheEndOfLimboItems.BLUE_HILL_TOMATO.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_PLANTS = CREATIVE_MODE_TABS.register("mod_plants",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS.get()))
                    .title(Component.translatable("creativeModeTab.mod_plants"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_LOG.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_WOOD.get());
                        pOutput.accept(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get());
                        pOutput.accept(TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_PLANKS.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_PLANKS_CURSED.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_LEAVES.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_SIGN.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get());
                        pOutput.accept(TheEndOfLimboBlocks.ABYSS_SAPLING.get());
                        pOutput.accept(TheEndOfLimboItems.ABYSS_BOAT.get());
                        pOutput.accept(TheEndOfLimboItems.ABYSS_CHEST_BOAT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_MUSICS = CREATIVE_MODE_TABS.register("mod_musics",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.BAFANGLAICAI_MUSIC_DISC.get()))
                    .title(Component.translatable("creativeModeTab.mod_musics"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.AMBULANCE_BEAT_MUSIC_DISC.get());
                        pOutput.accept(TheEndOfLimboItems.BAFANGLAICAI_MUSIC_DISC.get());
                        pOutput.accept(TheEndOfLimboItems.DROP_IT_LIKE_ITS_HOT_MUSIC_DISC.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_TOOLS = CREATIVE_MODE_TABS.register("mod_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.ABSOLUTE_BLACK_SWORD.get()))
                    .title(Component.translatable("creativeModeTab.mod_tools"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_SWORD.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_PICKAXE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_SHOVEL.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_AXE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_HOE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_SWORD.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_PICKAXE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_SHOVEL.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_AXE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_HOE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_ARMORS = CREATIVE_MODE_TABS.register("mod_armors",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET.get()))
                    .title(Component.translatable("creativeModeTab.mod_armors"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_CHESTPLATE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_LEGGINGS.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_BLACK_BOOTS.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_HELMET.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_CHESTPLATE.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_LEGGINGS.get());
                        pOutput.accept(TheEndOfLimboItems.ABSOLUTE_WHITE_BOOTS.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}