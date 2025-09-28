package com.example.examplemod.recipe;

import com.example.examplemod.ExampleMod;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, ExampleMod.MOD_ID);

    public static final RegistryObject<RecipeSerializer<MysteryStandRecipe>> MYSTERY_STAND_SERIALIZER =
            SERIALIZERS.register("incantation", () -> MysteryStandRecipe.Serializer.INSTANCE);

    public static void register(IEventBus eventBus){
        SERIALIZERS.register(eventBus);
    }
}
