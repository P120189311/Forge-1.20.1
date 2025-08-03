package com.example.examplemod.datagen;

import com.example.examplemod.ExampleMod;
import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.item.ModItems;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Block to Ore
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.BLACKY.get())
                .requires(ModBlocks.ABSOLUTE_BLACK.get())
                .unlockedBy("has_absolute_black", has(ModBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "blacky_recipe"));
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.WHITY.get())
                .requires(ModBlocks.ABSOLUTE_WHITE.get())
                .unlockedBy("has_absolute_white", has(ModBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "whity_recipe"));

        //Ore to Block
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.BLACKY.get(), RecipeCategory.MISC,
                ModBlocks.ABSOLUTE_BLACK.get());
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, ModItems.WHITY.get(), RecipeCategory.MISC,
                ModBlocks.ABSOLUTE_WHITE.get());

        //Fence
        customFenceBuilder(ModBlocks.ABSOLUTE_BLACK_FENCE.get(), Ingredient.of(ModBlocks.ABSOLUTE_BLACK.get()), Ingredient.of(ModItems.BLACKY.get()), 6)
                .unlockedBy("has_blacky", has(ModItems.BLACKY.get()))
                .unlockedBy("has_absolute_black", has(ModBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_black_fence"));
        customFenceBuilder(ModBlocks.ABSOLUTE_WHITE_FENCE.get(), Ingredient.of(ModBlocks.ABSOLUTE_WHITE.get()), Ingredient.of(ModItems.WHITY.get()), 6)
                .unlockedBy("has_whity", has(ModItems.WHITY.get()))
                .unlockedBy("has_absolute_white", has(ModBlocks.ABSOLUTE_WHITE.get()))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, "absolute_white_fence_"));

        //Stairs
        stair(consumer, ModBlocks.ABSOLUTE_BLACK_STAIRS.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_stairs");
        stair(consumer, ModBlocks.ABSOLUTE_BLACK_STAIRS.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_stairs");

        //Slab
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ABSOLUTE_BLACK_SLAB.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_slab");
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, ModBlocks.ABSOLUTE_WHITE_SLAB.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_slab");

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
        wall(consumer, RecipeCategory.DECORATIONS, ModBlocks.ABSOLUTE_BLACK_WALL.get(), ModBlocks.ABSOLUTE_BLACK.get());
        wall(consumer, RecipeCategory.DECORATIONS, ModBlocks.ABSOLUTE_WHITE_WALL.get(), ModBlocks.ABSOLUTE_WHITE.get());

    }

    protected static void slab(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        slabBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }

    protected static void stair(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String recipeName) {
        stairBuilder(result, Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, new ResourceLocation(ExampleMod.MOD_ID, recipeName));
    }

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

    protected static void wall(Consumer<FinishedRecipe> p_251034_, RecipeCategory p_251148_, ItemLike p_250499_, ItemLike p_249970_) {
        wallBuilder(p_251148_, p_250499_, Ingredient.of(new ItemLike[]{p_249970_})).unlockedBy(getHasName(p_249970_), has(p_249970_)).save(p_251034_, new ResourceLocation(ExampleMod.MOD_ID, getSimpleRecipeName(p_250499_)));
    }

    protected static RecipeBuilder wallBuilder(RecipeCategory p_249083_, ItemLike p_250754_, Ingredient p_250311_) {
        return ShapedRecipeBuilder.shaped(p_249083_, p_250754_, 6).define('#', p_250311_).pattern("###").pattern("###");
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
}
