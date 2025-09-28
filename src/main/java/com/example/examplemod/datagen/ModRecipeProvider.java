package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.MinecartItem;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.StonecutterRecipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Ore to Block & Block to Ore
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BLACKY.get(), RecipeCategory.MISC,
                ModBlocks.ABSOLUTE_BLACK.get());
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.WHITY.get(), RecipeCategory.MISC,
                ModBlocks.ABSOLUTE_WHITE.get());

        //Stairs
        stair(consumer, ModBlocks.ABSOLUTE_BLACK_STAIRS.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_stairs");
        stair(consumer, ModBlocks.ABSOLUTE_WHITE_STAIRS.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_stairs");

        //Slab
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ABSOLUTE_BLACK_SLAB.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_slab");
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ABSOLUTE_WHITE_SLAB.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_slab");

        //Fence
        customFenceBuilder(ModBlocks.ABSOLUTE_BLACK_FENCE.get(), Ingredient.of(ModBlocks.ABSOLUTE_BLACK.get()), Ingredient.of(ModItems.BLACKY.get()), 6)
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_absolute_black", has(ModBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_black_fence"));
        customFenceBuilder(ModBlocks.ABSOLUTE_WHITE_FENCE.get(), Ingredient.of(ModBlocks.ABSOLUTE_WHITE.get()), Ingredient.of(ModItems.WHITY.get()), 6)
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_absolute_white", has(ModBlocks.ABSOLUTE_WHITE.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_white_fence"));

        //Fence Gate
        customFenceGateBuilder(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(), Ingredient.of(ModBlocks.ABSOLUTE_BLACK.get()), Ingredient.of(ModItems.BLACKY.get()), 6)
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_absolute_black", has(ModBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_black_fence_gate"));
        customFenceGateBuilder(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(), Ingredient.of(ModBlocks.ABSOLUTE_WHITE.get()), Ingredient.of(ModItems.WHITY.get()), 6)
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_absolute_white", has(ModBlocks.ABSOLUTE_WHITE.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_white_fence_gate"));

        //Wall
        wall(consumer, RecipeCategory.DECORATIONS, ModBlocks.ABSOLUTE_BLACK_WALL.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_wall");
        wall(consumer, RecipeCategory.DECORATIONS, ModBlocks.ABSOLUTE_WHITE_WALL.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_wall");

        //Button
        /*
        button(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_BLACK_BUTTON.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_button");
        button(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_WHITE_BUTTON.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_button");
        */

        //Pressure Plate
        pressurePlate(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_pressure_plate");
        pressurePlate(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_pressure_plate");

        //Door
        door(consumer, ModBlocks.ABSOLUTE_BLACK_DOOR.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_door");
        door(consumer, ModBlocks.ABSOLUTE_WHITE_DOOR.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_door");

        //Trapdoor
        trapDoor(consumer, ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_trapdoor");
        trapDoor(consumer, ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_trapdoor");

        //Sword
        SwordBuilder(ModItems.ABSOLUTE_BLACK_SWORD.get(), Ingredient.of(ModItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        SwordBuilder(ModItems.ABSOLUTE_WHITE_SWORD.get(), Ingredient.of(ModItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Pickaxe
        PickaxeBuilder(ModItems.ABSOLUTE_BLACK_PICKAXE.get(), Ingredient.of(ModItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        PickaxeBuilder(ModItems.ABSOLUTE_WHITE_PICKAXE.get(), Ingredient.of(ModItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Shovel
        ShovelBuilder(ModItems.ABSOLUTE_BLACK_SHOVEL.get(), Ingredient.of(ModItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        ShovelBuilder(ModItems.ABSOLUTE_WHITE_SHOVEL.get(), Ingredient.of(ModItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Axe
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            AxeBuilder(ModItems.ABSOLUTE_BLACK_AXE.get(), Ingredient.of(ModItems.BLACKY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_blacky", has(ModItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_black_axe_" + side));
        }
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            AxeBuilder(ModItems.ABSOLUTE_WHITE_AXE.get(), Ingredient.of(ModItems.WHITY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_white_axe_" + side));
        }

        //Hoe
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            HoeBuilder(ModItems.ABSOLUTE_BLACK_HOE.get(), Ingredient.of(ModItems.BLACKY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_blacky", has(ModItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_black_hoe_" + side));
        }
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            HoeBuilder(ModItems.ABSOLUTE_WHITE_HOE.get(), Ingredient.of(ModItems.WHITY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_white_hoe_" + side));
        }

        helmetRecipe(consumer, ModItems.ABSOLUTE_BLACK_HELMET.get(), ModItems.BLACKY.get());
        helmetRecipe(consumer, ModItems.ABSOLUTE_WHITE_HELMET.get(), ModItems.WHITY.get());

        chestplateRecipe(consumer, ModItems.ABSOLUTE_BLACK_CHESTPLATE.get(), ModItems.BLACKY.get());
        chestplateRecipe(consumer, ModItems.ABSOLUTE_WHITE_CHESTPLATE.get(), ModItems.WHITY.get());

        leggingsRecipe(consumer, ModItems.ABSOLUTE_BLACK_LEGGINGS.get(), ModItems.BLACKY.get());
        leggingsRecipe(consumer, ModItems.ABSOLUTE_WHITE_LEGGINGS.get(), ModItems.WHITY.get());

        bootsRecipe(consumer, ModItems.ABSOLUTE_BLACK_BOOTS.get(), ModItems.BLACKY.get());
        bootsRecipe(consumer, ModItems.ABSOLUTE_WHITE_BOOTS.get(), ModItems.WHITY.get());

        fruitToSeed(consumer, RecipeCategory.FOOD, ModItems.BLUE_HILL_TOMATO_SEEDS.get(), ModItems.BLUE_HILL_TOMATO.get(), 3,"blue_hill_tomato");
    }


    //Helpers ->

    //Stair
    protected static void stair(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String recipeName) {
        stairBuilder(result, Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }

    //Slab
    protected static void slab(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        slabBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }

    //Fence Gate
    protected static RecipeBuilder customFenceBuilder(ItemLike result, Ingredient main, Ingredient secondary, int count) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, count)
                .define('W', main)
                .define('#', secondary)
                .pattern("W#W")
                .pattern("W#W");
    }
    protected static RecipeBuilder customFenceGateBuilder(ItemLike result, Ingredient main, Ingredient secondary, int count) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result, count)
                .define('W', main)
                .define('#', secondary)
                .pattern("#W#")
                .pattern("#W#");
    }

    //Wall
    protected static void wall(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        wallBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
    protected static RecipeBuilder wallBuilder(RecipeCategory p_249083_, ItemLike p_250754_, Ingredient p_250311_) {
        return ShapedRecipeBuilder.shaped(p_249083_, p_250754_, 6).define('#', p_250311_).pattern("###").pattern("###");
    }

    //Button
    /*
    protected static void button(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        buttonBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
    protected static RecipeBuilder buttonBuilder(ItemLike p_176659_, Ingredient p_176660_) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, p_176659_).requires(p_176660_);
    }
    */

    //Pressure Plate
    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        pressurePlateBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
    protected static RecipeBuilder pressurePlateBuilder(RecipeCategory p_251447_, ItemLike p_251989_, Ingredient p_249211_) {
        return ShapedRecipeBuilder.shaped(p_251447_, p_251989_).define('#', p_249211_).pattern("##");
    }

    //Door
    protected static void door(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        doorBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
    protected static RecipeBuilder doorBuilder(ItemLike p_176671_, Ingredient p_176672_) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, p_176671_, 3).define('#', p_176672_).pattern("##").pattern("##").pattern("##");
    }

    //Trapdoor
    protected static void trapDoor(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        trapdoorBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
    protected static RecipeBuilder trapdoorBuilder(ItemLike p_176721_, Ingredient p_176722_) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, p_176721_, 2).define('#', p_176722_).pattern("###").pattern("###");
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_250423_, RecipeCategory p_250083_, ItemLike p_250042_,
                                                  RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @javax.annotation.Nullable String p_248641_,
                                                  String p_252237_, @javax.annotation.Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).requires(p_251911_).group(p_250414_).unlockedBy(getHasName(p_251911_),
                has(p_251911_)).save(p_250423_, new ResourceLocation(ExampleMod.MOD_ID, p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_).define('#', p_250042_).pattern("###").pattern("###").pattern("###")
                .group(p_248641_).unlockedBy(getHasName(p_250042_), has(p_250042_)).save(p_250423_, new ResourceLocation(ExampleMod.MOD_ID, p_250475_));
    }

    protected static RecipeBuilder SwordBuilder(ItemLike result, Ingredient blade, Ingredient handle) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('I', blade)
                .define('S', handle)
                .pattern("I")
                .pattern("I")
                .pattern("S");
    }

    protected static RecipeBuilder PickaxeBuilder(ItemLike result, Ingredient blade, Ingredient handle) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('I', blade)
                .define('S', handle)
                .pattern("III")
                .pattern(" S ")
                .pattern(" S ");
    }

    protected static RecipeBuilder ShovelBuilder(ItemLike result, Ingredient blade, Ingredient handle) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('I', blade)
                .define('S', handle)
                .pattern("I")
                .pattern("S")
                .pattern("S");
    }

    protected static ShapedRecipeBuilder AxeBuilder(ItemLike result, Ingredient blade, Ingredient handle, boolean isLeft) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('B', blade)
                .define('S', handle);
        if (isLeft) {
            builder.pattern("BB")
                    .pattern("BS")
                    .pattern(" S");
        } else {
            builder.pattern("BB")
                    .pattern("SB")
                    .pattern("S ");
        }
        return builder;
    }

    protected static ShapedRecipeBuilder HoeBuilder(ItemLike result, Ingredient blade, Ingredient handle, boolean isLeft) {
        ShapedRecipeBuilder builder = ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, result)
                .define('B', blade)
                .define('S', handle);
        if (isLeft) {
            builder.pattern("BB")
                    .pattern(" S")
                    .pattern(" S");
        } else {
            builder.pattern("BB")
                    .pattern("S ")
                    .pattern("S ");
        }
        return builder;
    }

    protected static void helmetRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .pattern("XXX")
                .pattern("X X")
                .define('X', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer);
    }

    protected static void chestplateRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .define('X', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer);
    }

    protected static void leggingsRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .define('X', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer);
    }

    protected static void bootsRecipe(Consumer<FinishedRecipe> consumer, ItemLike output, Item ingredient) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, output)
                .pattern("X X")
                .define('X', ingredient)
                .unlockedBy("has_" + getItemName(ingredient), has(ingredient))
                .save(consumer);
    }

    protected static void fruitToSeed(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, int count, String recipeName) {
        ShapelessRecipeBuilder.shapeless(category, result, count)
                .requires(material)
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }
}
