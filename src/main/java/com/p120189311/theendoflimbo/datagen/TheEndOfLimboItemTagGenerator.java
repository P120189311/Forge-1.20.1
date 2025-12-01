package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class TheEndOfLimboItemTagGenerator extends ItemTagsProvider {

    public TheEndOfLimboItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, TheEndOfLimboMod.MAIN, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR).add(
                TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET.get(),
                TheEndOfLimboItems.ABSOLUTE_BLACK_CHESTPLATE.get(),
                TheEndOfLimboItems.ABSOLUTE_BLACK_LEGGINGS.get(),
                TheEndOfLimboItems.ABSOLUTE_BLACK_BOOTS.get(),
                TheEndOfLimboItems.ABSOLUTE_WHITE_HELMET.get(),
                TheEndOfLimboItems.ABSOLUTE_WHITE_CHESTPLATE.get(),
                TheEndOfLimboItems.ABSOLUTE_WHITE_LEGGINGS.get(),
                TheEndOfLimboItems.ABSOLUTE_WHITE_BOOTS.get());

        this.tag(ItemTags.MUSIC_DISCS).add(
                TheEndOfLimboItems.AMBULANCE_BEAT_MUSIC_DISC.get(),
                TheEndOfLimboItems.BAFANGLAICAI_MUSIC_DISC.get(),
                TheEndOfLimboItems.DROP_IT_LIKE_ITS_HOT_MUSIC_DISC.get());

        this.tag(ItemTags.CREEPER_DROP_MUSIC_DISCS).add(
                TheEndOfLimboItems.AMBULANCE_BEAT_MUSIC_DISC.get(),
                TheEndOfLimboItems.BAFANGLAICAI_MUSIC_DISC.get(),
                TheEndOfLimboItems.DROP_IT_LIKE_ITS_HOT_MUSIC_DISC.get());

        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(TheEndOfLimboBlocks.ABYSS_LOG.get().asItem())
                .add(TheEndOfLimboBlocks.ABYSS_WOOD.get().asItem())
                .add(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get().asItem())
                .add(TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(TheEndOfLimboBlocks.ABYSS_PLANKS.get().asItem());
    }
}
