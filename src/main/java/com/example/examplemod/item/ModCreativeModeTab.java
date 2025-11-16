package com.example.examplemod.item;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ExampleMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCreativeModeTab {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ExampleMod.MOD_ID);

    public static final RegistryObject<CreativeModeTab> BLACK = CREATIVE_MODE_TABS.register("black",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLACKY.get()))
                    .title(Component.translatable("creativeModeTab.black"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BLACKY.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_ORE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_STAIRS.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_SLAB.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_FENCE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_WALL.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_BUTTON.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_DOOR.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> WHITE = CREATIVE_MODE_TABS.register("white",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.WHITY.get()))
                    .title(Component.translatable("creativeModeTab.white"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.WHITY.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_ORE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_STAIRS.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_SLAB.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_FENCE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_WALL.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_BUTTON.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_DOOR.get());
                        pOutput.accept(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_MISC = CREATIVE_MODE_TABS.register("mod_misc",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ETERNAL_FLAME.get()))
                    .title(Component.translatable("creativeModeTab.mod_misc"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.DOWSING_ROD.get());
                        pOutput.accept(ModItems.ETERNAL_FLAME.get());
                        pOutput.accept(ModItems.NOCTURNAL_DWIGHT.get());
                        pOutput.accept(ModItems.TRAPPED_GAMMA_RAY.get());
                        pOutput.accept(ModBlocks.JUMPY_BLOCK.get());
                        pOutput.accept(ModBlocks.DECAYING_HARMONY.get());
                        pOutput.accept(ModBlocks.MYSTERY_STAND.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_FOOD = CREATIVE_MODE_TABS.register("mod_food",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VOID_OF_KNOWLEDGE.get()))
                    .title(Component.translatable("creativeModeTab.mod_food"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.VOID_OF_KNOWLEDGE.get());
                        pOutput.accept(ModItems.BLUE_HILL_TOMATO.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_PLANTS = CREATIVE_MODE_TABS.register("mod_plants",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BLUE_HILL_TOMATO_SEEDS.get()))
                    .title(Component.translatable("creativeModeTab.mod_plants"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.BLUE_HILL_TOMATO_SEEDS.get());
                        pOutput.accept(ModBlocks.ABYSS_LOG.get());
                        pOutput.accept(ModBlocks.ABYSS_WOOD.get());
                        pOutput.accept(ModBlocks.STRIPPED_ABYSS_LOG.get());
                        pOutput.accept(ModBlocks.STRIPPED_ABYSS_WOOD.get());
                        pOutput.accept(ModBlocks.ABYSS_PLANKS.get());
                        pOutput.accept(ModBlocks.ABYSS_PLANKS_CURSED.get());
                        pOutput.accept(ModBlocks.ABYSS_LEAVES.get());
                        pOutput.accept(ModBlocks.ABYSS_SIGN.get());
                        pOutput.accept(ModBlocks.ABYSS_HANGING_SIGN.get());
                        pOutput.accept(ModBlocks.ABYSS_SAPLING.get());
                        pOutput.accept(ModItems.ABYSS_BOAT.get());
                        pOutput.accept(ModItems.ABYSS_CHEST_BOAT.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_MUSICS = CREATIVE_MODE_TABS.register("mod_musics",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.BAFANGLAICAI_MUSIC_DISC.get()))
                    .title(Component.translatable("creativeModeTab.mod_musics"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.AMBULANCE_BEAT_MUSIC_DISC.get());
                        pOutput.accept(ModItems.BAFANGLAICAI_MUSIC_DISC.get());
                        pOutput.accept(ModItems.DROP_IT_LIKE_ITS_HOT_MUSIC_DISC.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_TOOLS = CREATIVE_MODE_TABS.register("mod_tools",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ABSOLUTE_BLACK_SWORD.get()))
                    .title(Component.translatable("creativeModeTab.mod_tools"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_SWORD.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_PICKAXE.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_SHOVEL.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_AXE.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_HOE.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_SWORD.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_PICKAXE.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_SHOVEL.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_AXE.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_HOE.get());
                    })
                    .build());

    public static final RegistryObject<CreativeModeTab> MOD_ARMORS = CREATIVE_MODE_TABS.register("mod_armors",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ABSOLUTE_BLACK_HELMET.get()))
                    .title(Component.translatable("creativeModeTab.mod_armors"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_HELMET.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_CHESTPLATE.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_LEGGINGS.get());
                        pOutput.accept(ModItems.ABSOLUTE_BLACK_BOOTS.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_HELMET.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_CHESTPLATE.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_LEGGINGS.get());
                        pOutput.accept(ModItems.ABSOLUTE_WHITE_BOOTS.get());
                    })
                    .build());

    public static void register(IEventBus eventBus) { CREATIVE_MODE_TABS.register(eventBus); }
}