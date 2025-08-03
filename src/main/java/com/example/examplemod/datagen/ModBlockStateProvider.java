package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExampleMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        blockWithItem(ModBlocks.ABSOLUTE_BLACK);
        blockWithItem(ModBlocks.ABSOLUTE_BLACK_ORE);
        stairsBlock((StairBlock) ModBlocks.ABSOLUTE_BLACK_STAIRS.get(), modLoc("block/absolute_black"));
        slabBlock((SlabBlock) ModBlocks.ABSOLUTE_BLACK_SLAB.get(), modLoc("block/absolute_black"), modLoc("block/absolute_black"));
        fenceBlock((FenceBlock) ModBlocks.ABSOLUTE_BLACK_FENCE.get(), modLoc("block/absolute_black"));
        fenceGateBlock((FenceGateBlock) ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(), modLoc("block/absolute_black"));
        wallBlock((WallBlock) ModBlocks.ABSOLUTE_BLACK_WALL.get(), modLoc("block/absolute_black"));

        blockWithItem(ModBlocks.ABSOLUTE_WHITE);
        blockWithItem(ModBlocks.ABSOLUTE_WHITE_ORE);
        stairsBlock((StairBlock) ModBlocks.ABSOLUTE_WHITE_STAIRS.get(), modLoc("block/absolute_white"));
        slabBlock((SlabBlock) ModBlocks.ABSOLUTE_WHITE_SLAB.get(), modLoc("block/absolute_white"), modLoc("block/absolute_white"));
        fenceBlock((FenceBlock) ModBlocks.ABSOLUTE_WHITE_FENCE.get(), modLoc("block/absolute_white"));
        fenceGateBlock((FenceGateBlock) ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(), modLoc("block/absolute_white"));
        wallBlock((WallBlock) ModBlocks.ABSOLUTE_WHITE_WALL.get(), modLoc("block/absolute_white"));

        blockWithItem(ModBlocks.JUMPY_BLOCK);

        generateFenceInventoryModel(ModBlocks.ABSOLUTE_BLACK_FENCE, "absolute_black");
        generateFenceInventoryModel(ModBlocks.ABSOLUTE_WHITE_FENCE, "absolute_white");

        generateWallInventoryModel(ModBlocks.ABSOLUTE_BLACK_WALL, "absolute_black");
        generateWallInventoryModel(ModBlocks.ABSOLUTE_WHITE_WALL, "absolute_white");

        List<RegistryObject<? extends Block>> specialBlocks = List.of(
                ModBlocks.ABSOLUTE_BLACK_STAIRS,
                ModBlocks.ABSOLUTE_BLACK_SLAB,
                ModBlocks.ABSOLUTE_BLACK_FENCE,
                ModBlocks.ABSOLUTE_BLACK_FENCE_GATE,
                ModBlocks.ABSOLUTE_BLACK_WALL,
                ModBlocks.ABSOLUTE_WHITE_STAIRS,
                ModBlocks.ABSOLUTE_WHITE_SLAB,
                ModBlocks.ABSOLUTE_WHITE_FENCE,
                ModBlocks.ABSOLUTE_WHITE_FENCE_GATE,
                ModBlocks.ABSOLUTE_WHITE_WALL
        );

        specialBlocks.forEach(this::blockItem);
    }

    public void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void blockItem(RegistryObject<? extends Block> block) {
        String path = block.getId().getPath();

        if (block.get() instanceof FenceBlock) {
            // fences use *_inventory.json for item models
            itemModels().withExistingParent(path, modLoc("block/" + path + "_inventory"));
        } else if (block.get() instanceof FenceGateBlock) {
            itemModels().withExistingParent(path, modLoc("block/" + path));
            // fence gate item model usually matches block model, no _inventory needed
        } else if (block.get() instanceof WallBlock) {
            itemModels().withExistingParent(path, modLoc("block/" + path + "_inventory"));
        } else {
            itemModels().withExistingParent(path, modLoc("block/" + path));
        }
    }

    private void generateFenceInventoryModel(RegistryObject<? extends Block> fenceBlock, String textureName) {
        models().getBuilder(fenceBlock.getId().getPath() + "_inventory")
                .parent(new ModelFile.UncheckedModelFile("minecraft:block/fence_inventory"))
                .texture("texture", modLoc("block/" + textureName));
    }

    private void generateWallInventoryModel(RegistryObject<? extends Block> wallBlock, String textureName) {
        models().getBuilder(wallBlock.getId().getPath() + "_inventory")
                .parent(new ModelFile.UncheckedModelFile("minecraft:block/wall_inventory"))
                .texture("wall", modLoc("block/" + textureName));
    }
}

