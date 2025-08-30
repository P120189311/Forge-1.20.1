package com.example.examplemod.block;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.custom.CustomPressurePlateBlock;
import com.example.examplemod.block.custom.DecayingHarmony;
import com.example.examplemod.block.custom.JumpyBlock;
import com.example.examplemod.item.ModCreativeModeTab;
import com.example.examplemod.item.ModItems;
import com.example.examplemod.item.custom.JumpyBlockItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, ExampleMod.MOD_ID);

    // Normal Blocks
    public static final RegistryObject<Block> ABSOLUTE_BLACK = registerBlock("absolute_black",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(8f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_ORE = registerBlock("absolute_black_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6f)
                    .requiresCorrectToolForDrops(),UniformInt.of(4, 6)));
    public static final RegistryObject<Block> ABSOLUTE_WHITE = registerBlock("absolute_white",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(8f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_ORE = registerBlock("absolute_white_ore",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(6f)
                    .requiresCorrectToolForDrops(),UniformInt.of(4, 6)));

    // Misc Blocks
    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block",
            () -> new JumpyBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> MYSTERY_STAND = registerBlock("mystery_stand",
            () -> new Block(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .strength(3f)
                    .noOcclusion()
                    .requiresCorrectToolForDrops()));

    // Flowers
    public static final RegistryObject<Block> DECAYING_HARMONY = registerBlock("decaying_harmony",
            () -> new DecayingHarmony(BlockBehaviour.Properties.copy(Blocks.WITHER_ROSE).noOcclusion()));
    public static final RegistryObject<Block> POTTED_DECAYING_HARMONY = BLOCKS.register("potted_decaying_harmony",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT,ModBlocks.DECAYING_HARMONY,
                    BlockBehaviour.Properties.copy(Blocks.POTTED_WITHER_ROSE).noOcclusion()));

    // Black Other Blocks
    public static final RegistryObject<Block> ABSOLUTE_BLACK_STAIRS = registerBlock("absolute_black_stairs",
            () -> new StairBlock(() -> ModBlocks.ABSOLUTE_BLACK.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                            .strength(5f)
                            .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_SLAB = registerBlock("absolute_black_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_FENCE = registerBlock("absolute_black_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_FENCE_GATE = registerBlock("absolute_black_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    ,SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_WALL = registerBlock("absolute_black_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));

    // White Other Blocks
    public static final RegistryObject<Block> ABSOLUTE_WHITE_STAIRS = registerBlock("absolute_white_stairs",
            () -> new StairBlock(() -> ModBlocks.ABSOLUTE_WHITE.get().defaultBlockState(),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_SLAB = registerBlock("absolute_white_slab",
            () -> new SlabBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_FENCE = registerBlock("absolute_white_fence",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_FENCE_GATE = registerBlock("absolute_white_fence_gate",
            () -> new FenceGateBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()
                    ,SoundEvents.FENCE_GATE_OPEN, SoundEvents.FENCE_GATE_CLOSE));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_WALL = registerBlock("absolute_white_wall",
            () -> new WallBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(5f)
                    .requiresCorrectToolForDrops()));

    // Black Utility Blocks
    public static final RegistryObject<Block> ABSOLUTE_BLACK_BUTTON = registerBlock("absolute_black_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollission()
                    ,BlockSetType.IRON,10, false));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_PRESSURE_PLATE = registerBlock("absolute_black_pressure_plate",
            () -> new CustomPressurePlateBlock(PressurePlateBlock.Sensitivity.EVERYTHING, BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollission()
                    ,BlockSetType.IRON
                    , 5));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_DOOR = registerBlock("absolute_black_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    ,BlockSetType.IRON));
    public static final RegistryObject<Block> ABSOLUTE_BLACK_TRAPDOOR = registerBlock("absolute_black_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    ,BlockSetType.IRON));

    // White Utility Blocks
    public static final RegistryObject<Block> ABSOLUTE_WHITE_BUTTON = registerBlock("absolute_white_button",
            () -> new ButtonBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollission()
                    ,BlockSetType.IRON,40, false));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_PRESSURE_PLATE = registerBlock("absolute_white_pressure_plate",
            () -> new CustomPressurePlateBlock(PressurePlateBlock.Sensitivity.MOBS, BlockBehaviour.Properties.of().mapColor(MapColor.METAL)
                    .strength(2f)
                    .requiresCorrectToolForDrops()
                    .noCollission()
                    ,BlockSetType.IRON
                    , 40));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_DOOR = registerBlock("absolute_white_door",
            () -> new DoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    ,BlockSetType.IRON));
    public static final RegistryObject<Block> ABSOLUTE_WHITE_TRAPDOOR = registerBlock("absolute_white_trapdoor",
            () -> new TrapDoorBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.METAL)
                    .strength(4f)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
                    ,BlockSetType.IRON));

    private static <T extends Block> RegistryObject<T> registerBlockWithoutBlockItem(String name, Supplier<T> block) {
        return BLOCKS.register(name, block);
    }

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(),
                new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}