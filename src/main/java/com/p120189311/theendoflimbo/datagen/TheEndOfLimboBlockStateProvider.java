package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.block.custom.BlueHillTomatoCropBlock;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;
import java.util.function.Function;

public class TheEndOfLimboBlockStateProvider extends BlockStateProvider {

    public TheEndOfLimboBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, TheEndOfLimboMod.MAIN, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {

        //Black Blocks
        blockWithItem(TheEndOfLimboBlocks.ABSOLUTE_BLACK);
        blockWithItem(TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE);
        stairsBlock((StairBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get(), modLoc("block/absolute_black"));
        slabBlock((SlabBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get(), modLoc("block/absolute_black"), modLoc("block/absolute_black"));
        fenceBlock((FenceBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get(), modLoc("block/absolute_black"));
        fenceGateBlock((FenceGateBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(), modLoc("block/absolute_black"));
        wallBlock((WallBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get(), modLoc("block/absolute_black"));
        buttonBlock((ButtonBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON.get(), modLoc("block/absolute_black"));
        pressurePlateBlock((PressurePlateBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(), modLoc("block/absolute_black"));
        doorBlock((DoorBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(), modLoc("block/absolute_black_door_bottom"), modLoc("block/absolute_black_door_top"));
        trapdoorBlock((TrapDoorBlock) TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), modLoc("block/absolute_black_trapdoor"), true);

        //White Blocks
        blockWithItem(TheEndOfLimboBlocks.ABSOLUTE_WHITE);
        blockWithItem(TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE);
        stairsBlock((StairBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get(), modLoc("block/absolute_white"));
        slabBlock((SlabBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get(), modLoc("block/absolute_white"), modLoc("block/absolute_white"));
        fenceBlock((FenceBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get(), modLoc("block/absolute_white"));
        fenceGateBlock((FenceGateBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(), modLoc("block/absolute_white"));
        wallBlock((WallBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get(), modLoc("block/absolute_white"));
        buttonBlock((ButtonBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON.get(), modLoc("block/absolute_white"));
        pressurePlateBlock((PressurePlateBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(), modLoc("block/absolute_white"));
        doorBlock((DoorBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(), modLoc("block/absolute_white_door_bottom"), modLoc("block/absolute_white_door_top"));
        trapdoorBlock((TrapDoorBlock) TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), modLoc("block/absolute_white_trapdoor"), true);

        //Misc Blocks
        blockWithItem(TheEndOfLimboBlocks.JUMPY_BLOCK);
        simpleBlockWithItem(TheEndOfLimboBlocks.MYSTERY_STAND.get(),
                new ModelFile.UncheckedModelFile(modLoc("block/mystery_stand")));

        //Plants
        logBlock(((RotatedPillarBlock) TheEndOfLimboBlocks.ABYSS_LOG.get()));
        axisBlock(((RotatedPillarBlock) TheEndOfLimboBlocks.ABYSS_WOOD.get()), blockTexture(TheEndOfLimboBlocks.ABYSS_LOG.get()), blockTexture(TheEndOfLimboBlocks.ABYSS_LOG.get()));

        axisBlock(((RotatedPillarBlock) TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get()), blockTexture(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get()),
                new ResourceLocation(TheEndOfLimboMod.MAIN, "block/stripped_abyss_log_top"));
        axisBlock(((RotatedPillarBlock) TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get()), blockTexture(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get()),
                blockTexture(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get()));

        treeItem(TheEndOfLimboBlocks.ABYSS_LOG);
        treeItem(TheEndOfLimboBlocks.ABYSS_WOOD);
        treeItem(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG);
        treeItem(TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD);

        blockWithItem(TheEndOfLimboBlocks.ABYSS_PLANKS);
        blockWithItem(TheEndOfLimboBlocks.ABYSS_PLANKS_CURSED);

        leavesBlock(TheEndOfLimboBlocks.ABYSS_LEAVES);

        signBlock(((StandingSignBlock) TheEndOfLimboBlocks.ABYSS_SIGN.get()), ((WallSignBlock) TheEndOfLimboBlocks.ABYSS_WALL_SIGN.get()),
                blockTexture(TheEndOfLimboBlocks.ABYSS_PLANKS.get()));

        hangingSignBlock(TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get(), TheEndOfLimboBlocks.ABYSS_WALL_HANGING_SIGN.get(), blockTexture(TheEndOfLimboBlocks.ABYSS_PLANKS.get()));

        saplingBlock(TheEndOfLimboBlocks.ABYSS_SAPLING);
        simpleBlockWithItem(TheEndOfLimboBlocks.POTTED_ABYSS_SAPLING.get(), models().singleTexture("potted_abyss_sapling", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(TheEndOfLimboBlocks.ABYSS_SAPLING.get())).renderType("cutout"));

        // Flowers
        simpleBlockWithItem(TheEndOfLimboBlocks.DECAYING_HARMONY.get(), models().cross(blockTexture(TheEndOfLimboBlocks.DECAYING_HARMONY.get()).getPath(),
                blockTexture(TheEndOfLimboBlocks.DECAYING_HARMONY.get())).renderType("cutout"));
        simpleBlockWithItem(TheEndOfLimboBlocks.POTTED_DECAYING_HARMONY.get(), models().singleTexture("potted_decaying_harmony", new ResourceLocation("flower_pot_cross"), "plant",
                blockTexture(TheEndOfLimboBlocks.DECAYING_HARMONY.get())).renderType("cutout"));

        models().getBuilder("absolute_black_trapdoor")
                .parent(new ModelFile.UncheckedModelFile("block/template_trapdoor_bottom"))
                .texture("texture", modLoc("block/absolute_black_trapdoor"));
        models().getBuilder("absolute_white_trapdoor")
                .parent(new ModelFile.UncheckedModelFile("block/template_trapdoor_bottom"))
                .texture("texture", modLoc("block/absolute_white_trapdoor"));

        //Models
        generateFenceInventoryModel(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE, "absolute_black");
        generateFenceInventoryModel(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE, "absolute_white");

        generateWallInventoryModel(TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL, "absolute_black");
        generateWallInventoryModel(TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL, "absolute_white");

        generateDoorItemModel(TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR);
        generateDoorItemModel(TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR);

        generateTrapdoorItemModel(TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR);
        generateTrapdoorItemModel(TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR);

        makeBlueHillTomatoCrop((CropBlock) TheEndOfLimboBlocks.BLUE_HILL_TOMATO_CROP.get(), "blue_hill_tomato_stage", "blue_hill_tomato_stage");

        List<RegistryObject<? extends Block>> specialBlocks = List.of(

                //Black Blocks
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR,

                //White Blocks
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR
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

    private void treeItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockItem(blockRegistryObject.get(), new ModelFile.UncheckedModelFile(TheEndOfLimboMod.MAIN +
                ":block/" + ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath()));
    }

    private void leavesBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), new ResourceLocation("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void saplingBlock(RegistryObject<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(ForgeRegistries.BLOCKS.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ResourceLocation texture) {
        ModelFile sign = models().sign(name(signBlock), texture);
        hangingSignBlock(signBlock, wallSignBlock, sign);
    }

    public void hangingSignBlock(Block signBlock, Block wallSignBlock, ModelFile sign) {
        simpleBlock(signBlock, sign);
        simpleBlock(wallSignBlock, sign);
    }

    private String name(Block block) {
        return key(block).getPath();
    }

    private ResourceLocation key(Block block) {
        return ForgeRegistries.BLOCKS.getKey(block);
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

    public void makeBlueHillTomatoCrop(CropBlock block, String modelName, String textureName) {
        Function<BlockState, ConfiguredModel[]> function = state -> blueHillTomatoStates(state, block, modelName, textureName);

        getVariantBuilder(block).forAllStates(function);
    }
    private ConfiguredModel[] blueHillTomatoStates(BlockState state, CropBlock block, String modelName, String textureName) {
        ConfiguredModel[] models = new ConfiguredModel[1];
        models[0] = new ConfiguredModel(models().crop(modelName + state.getValue(((BlueHillTomatoCropBlock) block).getAgeProperty()),
                new ResourceLocation(TheEndOfLimboMod.MAIN, "block/" + textureName + state.getValue(((BlueHillTomatoCropBlock) block).getAgeProperty()))).renderType("cutout"));

        return models;
    }
}

