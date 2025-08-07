package com.example.examplemod.datagen;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    protected ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Black Blocks
        dropSelf(ModBlocks.ABSOLUTE_BLACK.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_STAIRS.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_SLAB.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_FENCE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_WALL.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_BUTTON.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get());

        //White Blocks
        dropSelf(ModBlocks.ABSOLUTE_WHITE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_STAIRS.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_SLAB.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_FENCE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_WALL.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_BUTTON.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get());

        //Misc Blocks
        dropSelf(ModBlocks.JUMPY_BLOCK.get());

        add(ModBlocks.ABSOLUTE_BLACK_ORE.get(),
                (block -> createOreDrop(ModBlocks.ABSOLUTE_BLACK_ORE.get(), ModItems.BLACKY.get())));
        add(ModBlocks.ABSOLUTE_WHITE_ORE.get(),
                (block -> createOreDrop(ModBlocks.ABSOLUTE_WHITE_ORE.get(), ModItems.WHITY.get())));
        add(ModBlocks.ABSOLUTE_BLACK_DOOR.get(), createDoorTable(ModBlocks.ABSOLUTE_BLACK_DOOR.get()));
        add(ModBlocks.ABSOLUTE_WHITE_DOOR.get(), createDoorTable(ModBlocks.ABSOLUTE_WHITE_DOOR.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
