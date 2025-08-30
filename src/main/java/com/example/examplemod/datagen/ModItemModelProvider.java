package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, ExampleMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Simple
        simpleItem(ModItems.BLACKY);
        simpleItem(ModItems.WHITY);
        simpleItem(ModItems.DOWSING_ROD);
        simpleItem(ModItems.VOID_OF_KNOWLEDGE);
        simpleItem(ModItems.ETERNAL_FLAME);
        simpleItem(ModItems.ABSOLUTE_BLACK_HELMET);
        simpleItem(ModItems.ABSOLUTE_BLACK_CHESTPLATE);
        simpleItem(ModItems.ABSOLUTE_BLACK_LEGGINGS);
        simpleItem(ModItems.ABSOLUTE_BLACK_BOOTS);
        simpleItem(ModItems.ABSOLUTE_WHITE_HELMET);
        simpleItem(ModItems.ABSOLUTE_WHITE_CHESTPLATE);
        simpleItem(ModItems.ABSOLUTE_WHITE_LEGGINGS);
        simpleItem(ModItems.ABSOLUTE_WHITE_BOOTS);

        // Blocks
        simpleBlockItem(ModBlocks.DECAYING_HARMONY);
        withExistingParent(ModBlocks.MYSTERY_STAND.getId().getPath(),
                modLoc("block/mystery_stand"));

        //Handheld
        handheldItem(ModItems.ABSOLUTE_BLACK_SWORD);
        handheldItem(ModItems.ABSOLUTE_BLACK_PICKAXE);
        handheldItem(ModItems.ABSOLUTE_BLACK_SHOVEL);
        handheldItem(ModItems.ABSOLUTE_BLACK_AXE);
        handheldItem(ModItems.ABSOLUTE_BLACK_HOE);
        handheldItem(ModItems.ABSOLUTE_WHITE_SWORD);
        handheldItem(ModItems.ABSOLUTE_WHITE_PICKAXE);
        handheldItem(ModItems.ABSOLUTE_WHITE_SHOVEL);
        handheldItem(ModItems.ABSOLUTE_WHITE_AXE);
        handheldItem(ModItems.ABSOLUTE_WHITE_HOE);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ExampleMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(ExampleMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(ExampleMod.MOD_ID,"block/" + item.getId().getPath()));
    }
}
