package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.Objects;

public class ModBlockStateProvider extends BlockStateProvider {

    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, ExampleMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Black Blocks
        blockWithItem(ModBlocks.ABSOLUTE_BLACK);
        blockWithItem(ModBlocks.ABSOLUTE_BLACK_ORE);
        stairsBlock((StairBlock) ModBlocks.ABSOLUTE_BLACK_STAIRS.get(), modLoc("block/absolute_black"));
        slabBlock((SlabBlock) ModBlocks.ABSOLUTE_BLACK_SLAB.get(), modLoc("block/absolute_black"), modLoc("block/absolute_black"));
        fenceBlock((FenceBlock) ModBlocks.ABSOLUTE_BLACK_FENCE.get(), modLoc("block/absolute_black"));
        fenceGateBlock((FenceGateBlock) ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(), modLoc("block/absolute_black"));
        wallBlock((WallBlock) ModBlocks.ABSOLUTE_BLACK_WALL.get(), modLoc("block/absolute_black"));
        buttonBlock((ButtonBlock) ModBlocks.ABSOLUTE_BLACK_BUTTON.get(), modLoc("block/absolute_black"));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(), modLoc("block/absolute_black"));
        doorBlock((DoorBlock) ModBlocks.ABSOLUTE_BLACK_DOOR.get(), modLoc("block/absolute_black_door_bottom"), modLoc("block/absolute_black_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), modLoc("block/absolute_black_trapdoor"), true);

        //White Blocks
        blockWithItem(ModBlocks.ABSOLUTE_WHITE);
        blockWithItem(ModBlocks.ABSOLUTE_WHITE_ORE);
        stairsBlock((StairBlock) ModBlocks.ABSOLUTE_WHITE_STAIRS.get(), modLoc("block/absolute_white"));
        slabBlock((SlabBlock) ModBlocks.ABSOLUTE_WHITE_SLAB.get(), modLoc("block/absolute_white"), modLoc("block/absolute_white"));
        fenceBlock((FenceBlock) ModBlocks.ABSOLUTE_WHITE_FENCE.get(), modLoc("block/absolute_white"));
        fenceGateBlock((FenceGateBlock) ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(), modLoc("block/absolute_white"));
        wallBlock((WallBlock) ModBlocks.ABSOLUTE_WHITE_WALL.get(), modLoc("block/absolute_white"));
        buttonBlock((ButtonBlock) ModBlocks.ABSOLUTE_WHITE_BUTTON.get(), modLoc("block/absolute_white"));
        pressurePlateBlock((PressurePlateBlock) ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(), modLoc("block/absolute_white"));
        doorBlock((DoorBlock) ModBlocks.ABSOLUTE_WHITE_DOOR.get(), modLoc("block/absolute_white_door_bottom"), modLoc("block/absolute_white_door_top"));
        trapdoorBlock((TrapDoorBlock) ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), modLoc("block/absolute_white_trapdoor"), true);

        //Misc Blocks
        blockWithItem(ModBlocks.JUMPY_BLOCK);

        // Flowers
        simpleBlockWithItem(ModBlocks.DECAYING_HARMONY.get(), models().cross(blockTexture(ModBlocks.DECAYING_HARMONY.get()).getPath(),
                blockTexture(ModBlocks.DECAYING_HARMONY.get())).renderType("cutout"));
        simpleBlockWithItem(ModBlocks.POTTED_DECAYING_HARMONY.get(), models().singleTexture("potted_decaying_harmony", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(ModBlocks.DECAYING_HARMONY.get())).renderType("cutout"));

        models().getBuilder("absolute_black_trapdoor")
                .parent(new ModelFile.UncheckedModelFile("block/template_trapdoor_bottom"))
                .texture("texture", modLoc("block/absolute_black_trapdoor"));
        models().getBuilder("absolute_white_trapdoor")
                .parent(new ModelFile.UncheckedModelFile("block/template_trapdoor_bottom"))
                .texture("texture", modLoc("block/absolute_white_trapdoor"));

        //Models
        generateFenceInventoryModel(ModBlocks.ABSOLUTE_BLACK_FENCE, "absolute_black");
        generateFenceInventoryModel(ModBlocks.ABSOLUTE_WHITE_FENCE, "absolute_white");

        generateWallInventoryModel(ModBlocks.ABSOLUTE_BLACK_WALL, "absolute_black");
        generateWallInventoryModel(ModBlocks.ABSOLUTE_WHITE_WALL, "absolute_white");

        generateDoorItemModel(ModBlocks.ABSOLUTE_BLACK_DOOR);
        generateDoorItemModel(ModBlocks.ABSOLUTE_WHITE_DOOR);

        generateTrapdoorItemModel(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR);
        generateTrapdoorItemModel(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR);

        List<RegistryObject<? extends Block>> specialBlocks = List.of(

                //Black Blocks
                ModBlocks.ABSOLUTE_BLACK_STAIRS,
                ModBlocks.ABSOLUTE_BLACK_SLAB,
                ModBlocks.ABSOLUTE_BLACK_FENCE,
                ModBlocks.ABSOLUTE_BLACK_FENCE_GATE,
                ModBlocks.ABSOLUTE_BLACK_WALL,
                ModBlocks.ABSOLUTE_BLACK_BUTTON,
                ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE,
                ModBlocks.ABSOLUTE_BLACK_DOOR,
                ModBlocks.ABSOLUTE_BLACK_TRAPDOOR,

                //White Blocks
                ModBlocks.ABSOLUTE_WHITE_STAIRS,
                ModBlocks.ABSOLUTE_WHITE_SLAB,
                ModBlocks.ABSOLUTE_WHITE_FENCE,
                ModBlocks.ABSOLUTE_WHITE_FENCE_GATE,
                ModBlocks.ABSOLUTE_WHITE_WALL,
                ModBlocks.ABSOLUTE_WHITE_BUTTON,
                ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE,
                ModBlocks.ABSOLUTE_WHITE_DOOR,
                ModBlocks.ABSOLUTE_WHITE_TRAPDOOR
        );

        specialBlocks.stream()
                .filter(b -> !(b.get() instanceof DoorBlock || b.get() instanceof TrapDoorBlock))
                .forEach(this::blockItem);
    }

    public void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }

    public void blockItem(RegistryObject<? extends Block> block) {
        String path = block.getId().getPath();

        if (block.get() instanceof FenceBlock) {
            itemModels().withExistingParent(path, modLoc("block/" + path + "_inventory"));
        } else if (block.get() instanceof FenceGateBlock) {
            itemModels().withExistingParent(path, modLoc("block/" + path));
        } else if (block.get() instanceof WallBlock) {
            itemModels().withExistingParent(path, modLoc("block/" + path + "_inventory"));
        }  else if (block.get() instanceof ButtonBlock) {
        itemModels().withExistingParent(path, mcLoc("block/button_inventory"))
                .texture("texture", modLoc("block/" + getBaseName(path)));
        }   else {
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

    private void generateDoorItemModel(RegistryObject<? extends Block> doorBlock) {
        itemModels().withExistingParent(
                doorBlock.getId().getPath(),
                mcLoc("item/generated")
        ).texture("layer0", modLoc("item/" + doorBlock.getId().getPath()));
    }

    private void generateTrapdoorItemModel(RegistryObject<? extends Block> trapdoorBlock) {
        itemModels().withExistingParent(
                        trapdoorBlock.getId().getPath(),
                        modLoc("block/" + trapdoorBlock.getId().getPath()))
                .renderType("cutout");
    }

    private String getBaseName(String blockPath) {
        if (blockPath.endsWith("_button")) {
            return blockPath.substring(0, blockPath.length() - "_button".length());
        }
        return blockPath;
    }
}

