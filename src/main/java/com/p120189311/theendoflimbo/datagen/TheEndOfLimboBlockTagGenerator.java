package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.util.TheEndOfLimboTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TheEndOfLimboBlockTagGenerator extends BlockTagsProvider {
    public TheEndOfLimboBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, TheEndOfLimboMod.MAIN, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(TheEndOfLimboTags.Blocks.DOWSING_ROD_VALUABLES)
                .add(TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get()).addTag(Tags.Blocks.ORES)
                .add(TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get()).addTag(Tags.Blocks.ORES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE).add(
                TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(),

                TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(),

                TheEndOfLimboBlocks.JUMPY_BLOCK.get(),
                TheEndOfLimboBlocks.MYSTERY_STAND.get()
                );

        this.tag(BlockTags.NEEDS_IRON_TOOL).add(
                TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON.get(),
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(),

                TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get()
                );

        this.tag(BlockTags.NEEDS_STONE_TOOL).add(
                TheEndOfLimboBlocks.JUMPY_BLOCK.get(),
                TheEndOfLimboBlocks.MYSTERY_STAND.get()
        );

        this.tag(BlockTags.FENCES).add(
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
        this.tag(BlockTags.WALLS).add(
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get(),
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get());

        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(TheEndOfLimboBlocks.ABYSS_LOG.get())
                .add(TheEndOfLimboBlocks.ABYSS_WOOD.get())
                .add(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get())
                .add(TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get());

        this.tag(BlockTags.PLANKS)
                .add(TheEndOfLimboBlocks.ABYSS_PLANKS.get());
    }
}
