package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagGenerator extends ItemTagsProvider {

    public ModItemTagGenerator(PackOutput p_275343_, CompletableFuture<HolderLookup.Provider> p_275729_, CompletableFuture<TagLookup<Block>> p_275322_, @Nullable ExistingFileHelper existingFileHelper) {
        super(p_275343_, p_275729_, p_275322_, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.TRIMMABLE_ARMOR).add(
                ModItems.ABSOLUTE_BLACK_HELMET.get(),
                ModItems.ABSOLUTE_BLACK_CHESTPLATE.get(),
                ModItems.ABSOLUTE_BLACK_LEGGINGS.get(),
                ModItems.ABSOLUTE_BLACK_BOOTS.get(),
                ModItems.ABSOLUTE_WHITE_HELMET.get(),
                ModItems.ABSOLUTE_WHITE_CHESTPLATE.get(),
                ModItems.ABSOLUTE_WHITE_LEGGINGS.get(),
                ModItems.ABSOLUTE_WHITE_BOOTS.get());
    }
}
