package com.p120189311.theendoflimbo.recipe;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<RecipeSerializer<MysteryStandRecipe>> MYSTERY_STAND_SERIALIZER =
            SERIALIZERS.register("incantation", () -> MysteryStandRecipe.Serializer.INSTANCE);

    public static final RegistryObject<RecipeSerializer<PurificationRecipe>> PURIFICATION_SERIALIZER =
            SERIALIZERS.register("purification", () -> PurificationRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
