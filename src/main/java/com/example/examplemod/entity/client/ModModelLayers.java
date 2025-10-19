package com.example.examplemod.entity.client;

import com.example.examplemod.ExampleMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModModelLayers {
    public static final ModelLayerLocation ABYSS_BOAT_LAYERS = new ModelLayerLocation(
            new ResourceLocation(ExampleMod.MOD_ID, "boat/abyss"), "main");
    public static final ModelLayerLocation ABYSS_CHEST_BOAT_LAYERS = new ModelLayerLocation(
            new ResourceLocation(ExampleMod.MOD_ID, "chest_boat/abyss"), "main");
}
