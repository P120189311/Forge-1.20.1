package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.util.TheEndOfLimboTags;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.function.Consumer;

public class TheEndOfLimboRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public TheEndOfLimboRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {

        //Ore to Block & Block to Ore
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, TheEndOfLimboItems.BLACKY.get(), RecipeCategory.MISC,
                TheEndOfLimboBlocks.ABSOLUTE_BLACK.get());
        nineBlockStorageRecipes(consumer, RecipeCategory.BUILDING_BLOCKS, TheEndOfLimboItems.WHITY.get(), RecipeCategory.MISC,
                TheEndOfLimboBlocks.ABSOLUTE_WHITE.get());

        //Stairs
        stair(consumer, TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_stairs");
        stair(consumer, TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_stairs");

        //Slab
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_slab");
        slab(consumer, RecipeCategory.BUILDING_BLOCKS, TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_slab");

        //Fence
        customFenceBuilder(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get(), Ingredient.of(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get()), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), 6)
                .unlockedBy("has_blacky", has(TheEndOfLimboItems.BLACKY.get()))
                .unlockedBy("has_absolute_black", has(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_black_fence"));
        customFenceBuilder(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get(), Ingredient.of(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get()), Ingredient.of(TheEndOfLimboItems.WHITY.get()), 6)
                .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                .unlockedBy("has_absolute_white", has(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get()))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_white_fence"));

        //Fence Gate
        customFenceGateBuilder(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get(), Ingredient.of(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get()), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), 6)
                .unlockedBy("has_blacky", has(TheEndOfLimboItems.BLACKY.get()))
                .unlockedBy("has_absolute_black", has(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get()))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_black_fence_gate"));
        customFenceGateBuilder(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get(), Ingredient.of(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get()), Ingredient.of(TheEndOfLimboItems.WHITY.get()), 6)
                .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                .unlockedBy("has_absolute_white", has(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get()))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_white_fence_gate"));

        //Wall
        wall(consumer, RecipeCategory.DECORATIONS, TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_wall");
        wall(consumer, RecipeCategory.DECORATIONS, TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_wall");

        //Button
        /*
        button(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_BLACK_BUTTON.get(), ModBlocks.ABSOLUTE_BLACK.get(), "absolute_black_button");
        button(consumer, RecipeCategory.REDSTONE, ModBlocks.ABSOLUTE_WHITE_BUTTON.get(), ModBlocks.ABSOLUTE_WHITE.get(), "absolute_white_button");
        */

        //Pressure Plate
        pressurePlate(consumer, RecipeCategory.REDSTONE, TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_pressure_plate");
        pressurePlate(consumer, RecipeCategory.REDSTONE, TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_pressure_plate");

        //Door
        door(consumer, TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_door");
        door(consumer, TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_door");

        //Trapdoor
        trapDoor(consumer, TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get(), TheEndOfLimboBlocks.ABSOLUTE_BLACK.get(), "absolute_black_trapdoor");
        trapDoor(consumer, TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get(), TheEndOfLimboBlocks.ABSOLUTE_WHITE.get(), "absolute_white_trapdoor");

        //Log to Planks
        planksFromLog(consumer, TheEndOfLimboBlocks.ABYSS_PLANKS_CURSED.get(), TheEndOfLimboTags.Items.ABYSS_LOGS, 4);

        //Log to Wood
        woodFromLogs(consumer, TheEndOfLimboBlocks.ABYSS_WOOD.get(), TheEndOfLimboBlocks.ABYSS_LOG.get());
        woodFromLogs(consumer, TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get(), TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get());

        //Boat
        boat(consumer, TheEndOfLimboBlocks.ABYSS_PLANKS.get(), TheEndOfLimboItems.ABYSS_BOAT.get());

        //Boat With Chest
        boatWithChest(consumer, TheEndOfLimboItems.ABYSS_BOAT.get(), TheEndOfLimboItems.ABYSS_CHEST_BOAT.get());

        //Chest
        chest(consumer, Items.CHEST, TheEndOfLimboTags.Items.PLANKS);

        //Bookshelf
        bookshelf(consumer, Items.BOOKSHELF, TheEndOfLimboTags.Items.PLANKS);

        //Bowl
        bowl(consumer, Items.BOWL, TheEndOfLimboTags.Items.PLANKS);

        //Cartography Table
        cartographyTable(consumer, Items.CARTOGRAPHY_TABLE, TheEndOfLimboTags.Items.PLANKS);

        //Crafting Table
        craftingTable(consumer, Items.CRAFTING_TABLE, TheEndOfLimboTags.Items.PLANKS);

        //Fletching Table
        fletchingTable(consumer, Items.FLETCHING_TABLE, TheEndOfLimboTags.Items.PLANKS);

        //Grindstone
        grindstone(consumer, Items.GRINDSTONE, TheEndOfLimboTags.Items.PLANKS);

        //Jukebox
        jukebox(consumer, Items.JUKEBOX, TheEndOfLimboTags.Items.PLANKS);

        //Loom
        loom(consumer, Items.LOOM, TheEndOfLimboTags.Items.PLANKS);

        //Note Block
        noteBlock(consumer, Items.NOTE_BLOCK, TheEndOfLimboTags.Items.PLANKS);

        //Piston
        piston(consumer, Items.PISTON, TheEndOfLimboTags.Items.PLANKS);

        //Smithing Table
        smithingTable(consumer, Items.SMITHING_TABLE, TheEndOfLimboTags.Items.PLANKS);

        //Tripwire Hook
        tripwireHook(consumer, Items.TRIPWIRE_HOOK, TheEndOfLimboTags.Items.PLANKS);

        //Stick
        sticksFromPlanks(consumer, Items.STICK, TheEndOfLimboTags.Items.PLANKS);

        //Sign
        sign(consumer, TheEndOfLimboBlocks.ABYSS_SIGN.get(), TheEndOfLimboBlocks.ABYSS_PLANKS.get(), "abyss_sign");
        hangingSignBuilder(consumer, TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get(), TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get());

        //Smoker
        smoker(consumer, Items.SMOKER, Items.FURNACE, TheEndOfLimboTags.Items.ABYSS_LOGS);

        //Campfire
        campfire(consumer, Items.CAMPFIRE, TheEndOfLimboTags.Items.ABYSS_LOGS, Items.COAL);

        //Soul Campfire
        soulCampfire(consumer, Items.SOUL_CAMPFIRE, TheEndOfLimboTags.Items.ABYSS_LOGS, Items.SOUL_SOIL);

        //Beehive
        beehive(consumer, Items.BEEHIVE, TheEndOfLimboTags.Items.PLANKS, Items.HONEYCOMB);

        //Sword
        SwordBuilder(TheEndOfLimboItems.ABSOLUTE_BLACK_SWORD.get(), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(TheEndOfLimboItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        SwordBuilder(TheEndOfLimboItems.ABSOLUTE_WHITE_SWORD.get(), Ingredient.of(TheEndOfLimboItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Pickaxe
        PickaxeBuilder(TheEndOfLimboItems.ABSOLUTE_BLACK_PICKAXE.get(), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(TheEndOfLimboItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        PickaxeBuilder(TheEndOfLimboItems.ABSOLUTE_WHITE_PICKAXE.get(), Ingredient.of(TheEndOfLimboItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Shovel
        ShovelBuilder(TheEndOfLimboItems.ABSOLUTE_BLACK_SHOVEL.get(), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_blacky", has(TheEndOfLimboItems.BLACKY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);
        ShovelBuilder(TheEndOfLimboItems.ABSOLUTE_WHITE_SHOVEL.get(), Ingredient.of(TheEndOfLimboItems.WHITY.get()), Ingredient.of(Items.STICK))
                .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                .unlockedBy("has_stick", has(Items.STICK))
                .save(consumer);

        //Axe
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            AxeBuilder(TheEndOfLimboItems.ABSOLUTE_BLACK_AXE.get(), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_blacky", has(TheEndOfLimboItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_black_axe_" + side));
        }
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            AxeBuilder(TheEndOfLimboItems.ABSOLUTE_WHITE_AXE.get(), Ingredient.of(TheEndOfLimboItems.WHITY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_white_axe_" + side));
        }

        //Hoe
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            HoeBuilder(TheEndOfLimboItems.ABSOLUTE_BLACK_HOE.get(), Ingredient.of(TheEndOfLimboItems.BLACKY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_blacky", has(TheEndOfLimboItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_black_hoe_" + side));
        }
        for (int i = 0; i < 2; i++) {
            boolean isLeft = i == 0;
            String side = isLeft ? "left" : "right";
            HoeBuilder(TheEndOfLimboItems.ABSOLUTE_WHITE_HOE.get(), Ingredient.of(TheEndOfLimboItems.WHITY.get()), Ingredient.of(Items.STICK), isLeft)
                    .unlockedBy("has_whity", has(TheEndOfLimboItems.WHITY.get()))
                    .unlockedBy("has_stick", has(Items.STICK))
                    .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, "absolute_white_hoe_" + side));
        }

        //Shield
        shield(consumer, Items.SHIELD, TheEndOfLimboTags.Items.PLANKS);

        //Armor
        helmetRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET.get(), TheEndOfLimboItems.BLACKY.get());
        helmetRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_WHITE_HELMET.get(), TheEndOfLimboItems.WHITY.get());

        chestplateRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_BLACK_CHESTPLATE.get(), TheEndOfLimboItems.BLACKY.get());
        chestplateRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_WHITE_CHESTPLATE.get(), TheEndOfLimboItems.WHITY.get());

        leggingsRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_BLACK_LEGGINGS.get(), TheEndOfLimboItems.BLACKY.get());
        leggingsRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_WHITE_LEGGINGS.get(), TheEndOfLimboItems.WHITY.get());

        bootsRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_BLACK_BOOTS.get(), TheEndOfLimboItems.BLACKY.get());
        bootsRecipe(consumer, TheEndOfLimboItems.ABSOLUTE_WHITE_BOOTS.get(), TheEndOfLimboItems.WHITY.get());

        fruitToSeed(consumer, RecipeCategory.FOOD, TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS.get(), TheEndOfLimboItems.BLUE_HILL_TOMATO.get(), 3,"blue_hill_tomato");
    }


    //Helpers ->

    //Stair
    protected static void stair(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike input, String recipeName) {
        stairBuilder(result, Ingredient.of(input))
                .unlockedBy(getHasName(input), has(input))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }

    //Slab
    protected static void slab(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        slabBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
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
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }
    protected static RecipeBuilder wallBuilder(RecipeCategory p_249083_, ItemLike p_250754_, Ingredient p_250311_) {
        return ShapedRecipeBuilder.shaped(p_249083_, p_250754_, 6).define('#', p_250311_).pattern("###").pattern("###");
    }

    //Button
    /*
    protected static void button(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        buttonBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimbo.MOD_ID, recipeName));
    }
    protected static RecipeBuilder buttonBuilder(ItemLike p_176659_, Ingredient p_176660_) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.REDSTONE, p_176659_).requires(p_176660_);
    }
    */

    //Pressure Plate
    protected static void pressurePlate(Consumer<FinishedRecipe> consumer, RecipeCategory category, ItemLike result, ItemLike material, String recipeName) {
        pressurePlateBuilder(category, result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }
    protected static RecipeBuilder pressurePlateBuilder(RecipeCategory p_251447_, ItemLike p_251989_, Ingredient p_249211_) {
        return ShapedRecipeBuilder.shaped(p_251447_, p_251989_).define('#', p_249211_).pattern("##");
    }

    //Door
    protected static void door(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        doorBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }
    protected static RecipeBuilder doorBuilder(ItemLike p_176671_, Ingredient p_176672_) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, p_176671_, 3).define('#', p_176672_).pattern("##").pattern("##").pattern("##");
    }

    //Trapdoor
    protected static void trapDoor(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        trapdoorBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }
    protected static RecipeBuilder trapdoorBuilder(ItemLike p_176721_, Ingredient p_176722_) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, p_176721_, 2).define('#', p_176722_).pattern("###").pattern("###");
    }

    //Bee Hive
    protected static void beehive(Consumer<FinishedRecipe> consumer, ItemLike beehive, TagKey<Item> planksTag, ItemLike honeycomb) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, beehive)
                .define('W', planksTag)
                .define('S', honeycomb)
                .pattern("WWW")
                .pattern("WSW")
                .pattern("WWW")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, getItemName(beehive) + "_from_planks_and_honeycomb"));
    }

    // Log to Wood
    protected static void woodFromLogs(Consumer<FinishedRecipe> consumer, ItemLike wood, ItemLike log) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, wood, 3)
                .define('#', log)
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(log), has(log))
                .save(consumer);
    }

    //Boat
    protected static void boat(Consumer<FinishedRecipe> consumer, ItemLike planks, ItemLike boatItem){
        ShapedRecipeBuilder.shaped(RecipeCategory.TRANSPORTATION, boatItem)
                .define('#', planks)
                .pattern("# #")
                .pattern("###")
                .unlockedBy(getHasName(planks), has(planks))
                .save(consumer);
    }

    //Boat With Chest
    protected static void boatWithChest(Consumer<FinishedRecipe> consumer, ItemLike boat, ItemLike chestBoat){
        ShapelessRecipeBuilder.shapeless(RecipeCategory.TRANSPORTATION, chestBoat)
                .requires(Items.CHEST)
                .requires(boat)
                .unlockedBy(getHasName(boat), has(boat))
                .save(consumer, new ResourceLocation(
                        TheEndOfLimboMod.MAIN,
                        getItemName(chestBoat)));
    }

    //Bed
    protected static void bedFromPlanksAndWool(Consumer<FinishedRecipe> consumer, ItemLike bed, TagKey<Item> planksTag, TagKey<Item> woolTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, bed)
                .define('W', woolTag)
                .define('P', planksTag)
                .pattern("WWW")
                .pattern("PPP")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, getItemName(bed) + "_from_planks_and_wool"));
    }

    //Chest
    protected static void chest(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .pattern("###")
                .pattern("# #")
                .pattern("###")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Bookshelf
    protected static void bookshelf(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, result)
                .define('#', planksTag)
                .define('B', Items.BOOK)
                .pattern("###")
                .pattern("BBB")
                .pattern("###")
                .unlockedBy("has_books", has(Items.BOOK))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Bowl
    protected static void bowl(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, 4)
                .define('#', planksTag)
                .pattern("# #")
                .pattern(" # ")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Cartography Table
    protected static void cartographyTable(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .define('P', Items.PAPER)
                .pattern("PP")
                .pattern("##")
                .unlockedBy("has_paper", has(Items.PAPER))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Crafting Table
    protected static void craftingTable(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Fletching Table
    protected static void fletchingTable(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .define('F', Items.FLINT)
                .pattern("FF")
                .pattern("##")
                .unlockedBy("has_flint", has(Items.FLINT))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Grind Stone
    protected static void grindstone(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .define('S', Items.STONE_SLAB)
                .define('I', Items.STICK)
                .pattern("I#I")
                .pattern(" S ")
                .unlockedBy("has_stone_slab", has(Items.STONE_SLAB))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Jukebox
    protected static void jukebox(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result)
                .define('#', planksTag)
                .define('D', Items.DIAMOND)
                .pattern("###")
                .pattern("#D#")
                .pattern("###")
                .unlockedBy("has_diamond", has(Items.DIAMOND))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Loom
    protected static void loom(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .define('S', Items.STRING)
                .pattern("SS")
                .pattern("##")
                .unlockedBy("has_string", has(Items.STRING))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Note Block
    protected static void noteBlock(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result)
                .define('#', planksTag)
                .define('R', Items.REDSTONE)
                .pattern("###")
                .pattern("#R#")
                .pattern("###")
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Piston
    protected static void piston(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result)
                .define('#', planksTag)
                .define('S', Items.COBBLESTONE)
                .define('I', Items.IRON_INGOT)
                .define('R', Items.REDSTONE)
                .pattern("###")
                .pattern("SIS")
                .pattern("SRS")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_redstone", has(Items.REDSTONE))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Smithing Table
    protected static void smithingTable(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, result)
                .define('#', planksTag)
                .define('I', Items.IRON_INGOT)
                .pattern("II")
                .pattern("##")
                .pattern("##")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    //Tripwire Hook
    protected static void tripwireHook(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.REDSTONE, result, 2)
                .define('#', planksTag)
                .define('I', Items.IRON_INGOT)
                .define('S', Items.STICK)
                .pattern("I")
                .pattern("S")
                .pattern("#")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
    }

    // Stick
    protected static void sticksFromPlanks(Consumer<FinishedRecipe> consumer, ItemLike stickOutput, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, stickOutput, 4)
                .pattern("#")
                .pattern("#")
                .define('#', planksTag)
                .group("sticks")
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer, new ResourceLocation("theendoflimbo", "sticks_from_" + planksTag.location().getPath()));
    }

    // Sign
    protected static void sign(Consumer<FinishedRecipe> consumer, ItemLike result, ItemLike material, String recipeName) {
        signBuilder(result, Ingredient.of(material))
                .unlockedBy(getHasName(material), has(material))
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }

    //Smoker
    protected static void smoker(Consumer<FinishedRecipe> consumer, ItemLike smoker, ItemLike furnace, TagKey<Item> logsTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, smoker)
                .define('#', logsTag)
                .define('F', furnace)
                .pattern("###")
                .pattern("#F#")
                .pattern("###")
                .unlockedBy("has_log", has(logsTag))
                .save(consumer);
    }

    //Campfire
    protected static void campfire(Consumer<FinishedRecipe> consumer, ItemLike campfire, TagKey<Item> logsTag, ItemLike coal) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, campfire)
                .define('C', coal)
                .define('#', logsTag)
                .define('S', Items.STICK)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("###")
                .unlockedBy("has_log", has(logsTag))
                .save(consumer);
    }

    //Soul Campfire
    protected static void soulCampfire(Consumer<FinishedRecipe> consumer, ItemLike soulCampfire, TagKey<Item> logsTag, ItemLike soulSoil) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, soulCampfire)
                .define('C', soulSoil)
                .define('#', logsTag)
                .define('S', Items.STICK)
                .pattern(" S ")
                .pattern("SCS")
                .pattern("###")
                .unlockedBy("has_log", has(logsTag))
                .save(consumer);
    }

    protected static void hangingSignBuilder(Consumer<FinishedRecipe> p_250663_, ItemLike p_252355_, ItemLike p_250437_) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, p_252355_, 6).group("hanging_sign").define('#', p_250437_).define('X', Items.CHAIN).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_logs", has(p_250437_)).save(p_250663_);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_249580_, RecipeCategory p_251203_, ItemLike p_251689_, RecipeCategory p_251376_, ItemLike p_248771_) {
        nineBlockStorageRecipes(p_249580_, p_251203_, p_251689_, p_251376_, p_248771_, getSimpleRecipeName(p_248771_), (String)null, getSimpleRecipeName(p_251689_), (String)null);
    }

    protected static void nineBlockStorageRecipes(Consumer<FinishedRecipe> p_250423_, RecipeCategory p_250083_, ItemLike p_250042_,
                                                  RecipeCategory p_248977_, ItemLike p_251911_, String p_250475_, @javax.annotation.Nullable String p_248641_,
                                                  String p_252237_, @javax.annotation.Nullable String p_250414_) {
        ShapelessRecipeBuilder.shapeless(p_250083_, p_250042_, 9).requires(p_251911_).group(p_250414_).unlockedBy(getHasName(p_251911_),
                has(p_251911_)).save(p_250423_, new ResourceLocation(TheEndOfLimboMod.MAIN, p_252237_));
        ShapedRecipeBuilder.shaped(p_248977_, p_251911_).define('#', p_250042_).pattern("###").pattern("###").pattern("###")
                .group(p_248641_).unlockedBy(getHasName(p_250042_), has(p_250042_)).save(p_250423_, new ResourceLocation(TheEndOfLimboMod.MAIN, p_250475_));
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

    //Shield
    protected static void shield(Consumer<FinishedRecipe> consumer, ItemLike result, TagKey<Item> planksTag) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, result)
                .define('#', planksTag)
                .define('I', Items.IRON_INGOT)
                .pattern("#I#")
                .pattern("###")
                .pattern(" # ")
                .unlockedBy("has_iron_ingot", has(Items.IRON_INGOT))
                .unlockedBy("has_planks", has(planksTag))
                .save(consumer);
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
                .save(consumer, new ResourceLocation(TheEndOfLimboMod.MAIN, recipeName));
    }
}
