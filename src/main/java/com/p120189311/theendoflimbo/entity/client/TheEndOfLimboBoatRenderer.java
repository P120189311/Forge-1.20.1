package com.p120189311.theendoflimbo.entity.client;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.google.common.collect.ImmutableMap;
import com.mojang.datafixers.util.Pair;
import com.p120189311.theendoflimbo.entity.custom.AbyssBoatEntity;
import com.p120189311.theendoflimbo.entity.custom.AbyssChestBoatEntity;
import net.minecraft.client.model.BoatModel;
import net.minecraft.client.model.ChestBoatModel;
import net.minecraft.client.model.ListModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.entity.BoatRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.vehicle.Boat;

import java.util.Map;
import java.util.stream.Stream;

public class TheEndOfLimboBoatRenderer extends BoatRenderer {
    private final Map<AbyssBoatEntity.Type, Pair<ResourceLocation, ListModel<Boat>>> boatResources;

    public TheEndOfLimboBoatRenderer(EntityRendererProvider.Context pContext, boolean pChestBoat) {
        super(pContext, pChestBoat);
        this.boatResources = Stream.of(AbyssBoatEntity.Type.values()).collect(ImmutableMap.toImmutableMap(type -> type,
                type -> Pair.of(new ResourceLocation(TheEndOfLimboMod.MAIN, getTextureLocation(type, pChestBoat)), this.createBoatModel(pContext, type, pChestBoat))));
    }

    private static String getTextureLocation(AbyssBoatEntity.Type pType, boolean pChestBoat) {
        return pChestBoat ? "textures/entity/chest_boat/" + pType.getName() + ".png" : "textures/entity/boat/" + pType.getName() + ".png";
    }

    private ListModel<Boat> createBoatModel(EntityRendererProvider.Context pContext, AbyssBoatEntity.Type pType, boolean pChestBoat) {
        ModelLayerLocation modellayerlocation = pChestBoat ? TheEndOfLimboBoatRenderer.createChestBoatModelName(pType) : TheEndOfLimboBoatRenderer.createBoatModelName(pType);
        ModelPart modelpart = pContext.bakeLayer(modellayerlocation);
        return pChestBoat ? new ChestBoatModel(modelpart) : new BoatModel(modelpart);
    }

    public static ModelLayerLocation createBoatModelName(AbyssBoatEntity.Type pType) {
        return createLocation("boat/" + pType.getName(), "main");
    }

    public static ModelLayerLocation createChestBoatModelName(AbyssBoatEntity.Type pType) {
        return createLocation("chest_boat/" + pType.getName(), "main");
    }

    private static ModelLayerLocation createLocation(String pPath, String pModel) {
        return new ModelLayerLocation(new ResourceLocation(TheEndOfLimboMod.MAIN, pPath), pModel);
    }

    public Pair<ResourceLocation, ListModel<Boat>> getModelWithLocation(Boat boat) {
        if(boat instanceof AbyssBoatEntity modBoat) {
            return this.boatResources.get(modBoat.getModVariant());
        } else if(boat instanceof AbyssChestBoatEntity modChestBoatEntity) {
            return this.boatResources.get(modChestBoatEntity.getModVariant());
        } else {
            return null;
        }
    }
}
