package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.DOWSING_ROD_VALUABLES)
                .add(ModBlocks.ABSOLUTE_BLACK_ORE.get()).addTag(Tags.Blocks.ORES)
                .add(ModBlocks.ABSOLUTE_WHITE_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                ModBlocks.ABSOLUTE_BLACK.get(),
                ModBlocks.ABSOLUTE_BLACK_ORE.get(),
                ModBlocks.ABSOLUTE_BLACK_STAIRS.get(),
                ModBlocks.ABSOLUTE_BLACK_SLAB.get(),
                ModBlocks.ABSOLUTE_BLACK_FENCE.get(),
                ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                ModBlocks.ABSOLUTE_BLACK_WALL.get(),
                ModBlocks.ABSOLUTE_BLACK_DOOR.get(),
                ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(),
                ModBlocks.ABSOLUTE_BLACK_BUTTON.get(),
                ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(),

                ModBlocks.ABSOLUTE_WHITE.get(),
                ModBlocks.ABSOLUTE_WHITE_ORE.get(),
                ModBlocks.ABSOLUTE_WHITE_STAIRS.get(),
                ModBlocks.ABSOLUTE_WHITE_SLAB.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(),
                ModBlocks.ABSOLUTE_WHITE_WALL.get(),
                ModBlocks.ABSOLUTE_WHITE_DOOR.get(),
                ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(),
                ModBlocks.ABSOLUTE_WHITE_BUTTON.get(),
                ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(),

                ModBlocks.JUMPY_BLOCK.get(),
                ModBlocks.MYSTERY_STAND.get()
                );

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                ModBlocks.ABSOLUTE_BLACK.get(),
                ModBlocks.ABSOLUTE_BLACK_ORE.get(),
                ModBlocks.ABSOLUTE_BLACK_STAIRS.get(),
                ModBlocks.ABSOLUTE_BLACK_SLAB.get(),
                ModBlocks.ABSOLUTE_BLACK_FENCE.get(),
                ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                ModBlocks.ABSOLUTE_BLACK_WALL.get(),
                ModBlocks.ABSOLUTE_BLACK_DOOR.get(),
                ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(),
                ModBlocks.ABSOLUTE_BLACK_BUTTON.get(),
                ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(),

                ModBlocks.ABSOLUTE_WHITE.get(),
                ModBlocks.ABSOLUTE_WHITE_ORE.get(),
                ModBlocks.ABSOLUTE_WHITE_STAIRS.get(),
                ModBlocks.ABSOLUTE_WHITE_SLAB.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(),
                ModBlocks.ABSOLUTE_WHITE_WALL.get(),
                ModBlocks.ABSOLUTE_WHITE_DOOR.get(),
                ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(),
                ModBlocks.ABSOLUTE_WHITE_BUTTON.get(),
                ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                ModBlocks.JUMPY_BLOCK.get(),
                ModBlocks.MYSTERY_STAND.get()
        );

        this.tag(BlockTags.FENCES).add(
                ModBlocks.ABSOLUTE_BLACK_FENCE.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(
                ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS).add(
                ModBlocks.ABSOLUTE_BLACK_WALL.get(),
                ModBlocks.ABSOLUTE_WHITE_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ABYSS_LOG.get())
                .add(ModBlocks.ABYSS_WOOD.get())
                .add(ModBlocks.STRIPPED_ABYSS_LOG.get())
                .add(ModBlocks.STRIPPED_ABYSS_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(ModBlocks.ABYSS_PLANKS.get());
    }
}
