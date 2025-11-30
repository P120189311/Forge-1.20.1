package com.p120189311.theendoflimbo.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PurificationRecipe implements Recipe<SimpleContainer> {

    private final ResourceLocation id;
    private final Ingredient input;
    private final Ingredient catalyst;
    private final ItemStack output;

    public PurificationRecipe(ResourceLocation id, Ingredient input, Ingredient catalyst, ItemStack output) {
        this.id = id;
        this.input = input;
        this.catalyst = catalyst;
        this.output = output;
    }

    public Ingredient getInput() {
        return input;
    }

    public Ingredient getCatalyst() {
        return catalyst;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public boolean matches(SimpleContainer container, Level level) {
        if (level.isClientSide) return false;

        return input.test(container.getItem(0)) &&
                catalyst.test(container.getItem(1));
    }

    @Override
    public ItemStack assemble(SimpleContainer container, RegistryAccess access) {
        return output.copy();
    }

    @Override
    public boolean canCraftInDimensions(int w, int h) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess access) {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<PurificationRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "purification";
    }

    public static class Serializer implements RecipeSerializer<PurificationRecipe> {

        public static final Serializer INSTANCE = new Serializer();
        public static final ResourceLocation ID =
                new ResourceLocation(TheEndOfLimboMod.MAIN, "purification");

        @Override
        public PurificationRecipe fromJson(ResourceLocation recipeId, JsonObject json) {

            Ingredient input = Ingredient.fromJson(json.get("input"));
            Ingredient catalyst = Ingredient.fromJson(json.get("catalyst"));
            ItemStack output = ShapedRecipe.itemStackFromJson(json.getAsJsonObject("output"));

            return new PurificationRecipe(recipeId, input, catalyst, output);
        }

        @Override
        public PurificationRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buf) {

            Ingredient input = Ingredient.fromNetwork(buf);
            Ingredient catalyst = Ingredient.fromNetwork(buf);
            ItemStack output = buf.readItem();

            return new PurificationRecipe(recipeId, input, catalyst, output);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buf, PurificationRecipe recipe) {

            recipe.input.toNetwork(buf);
            recipe.catalyst.toNetwork(buf);
            buf.writeItem(recipe.output);
        }
    }
}
