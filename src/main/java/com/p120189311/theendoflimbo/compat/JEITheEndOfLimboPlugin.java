package com.p120189311.theendoflimbo.compat;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.compat.jeicategories.IncantationCategory;
import com.p120189311.theendoflimbo.compat.jeicategories.LeafBurningCategory;
import com.p120189311.theendoflimbo.compat.jeicategories.PurificationCategory;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.recipe.LeafBurningRecipe;
import com.p120189311.theendoflimbo.recipe.MysteryStandRecipe;
import com.p120189311.theendoflimbo.recipe.PurificationRecipe;
import com.p120189311.theendoflimbo.screen.MysteryStandScreen;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.registration.IGuiHandlerRegistration;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JEITheEndOfLimboPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(TheEndOfLimboMod.MAIN, "jei_plugin");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        IGuiHelper guiHelper = registration.getJeiHelpers().getGuiHelper();
        registration.addRecipeCategories(
                new IncantationCategory(guiHelper),
                new PurificationCategory(guiHelper),
                new LeafBurningCategory(guiHelper));
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(TheEndOfLimboBlocks.MYSTERY_STAND.get()), IncantationCategory.MYSTERY_STAND_TYPE);
        registration.addRecipeCatalyst(new ItemStack(Items.FLINT_AND_STEEL), LeafBurningCategory.LEAF_BURNING_TYPE);
        registration.addRecipeCatalyst(new ItemStack(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get()), PurificationCategory.PURIFICATION_TYPE);
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<MysteryStandRecipe> incantationRecipes = recipeManager.getAllRecipesFor(MysteryStandRecipe.Type.INSTANCE);
        registration.addRecipes(IncantationCategory.MYSTERY_STAND_TYPE, incantationRecipes);

        List<PurificationRecipe> purificationRecipes = recipeManager.getAllRecipesFor(PurificationRecipe.Type.INSTANCE);
        registration.addRecipes(PurificationCategory.PURIFICATION_TYPE, purificationRecipes);

        registration.addRecipes(
                LeafBurningCategory.LEAF_BURNING_TYPE,
                List.of(new LeafBurningRecipe(
                        new ItemStack(TheEndOfLimboBlocks.ABYSS_LEAVES.get()),
                        new ItemStack(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get()),
                        0.01f
                ))
        );
    }

    @Override
    public void registerGuiHandlers(IGuiHandlerRegistration registration) {
        registration.addRecipeClickArea(MysteryStandScreen.class, 49,35,24,15, IncantationCategory.MYSTERY_STAND_TYPE);
        registration.addRecipeClickArea(MysteryStandScreen.class, 103,35,24,15, IncantationCategory.MYSTERY_STAND_TYPE);
    }
}
