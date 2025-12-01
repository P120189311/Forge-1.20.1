package com.p120189311.theendoflimbo.entity;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.entity.custom.AbyssBoatEntity;
import com.p120189311.theendoflimbo.entity.custom.AbyssChestBoatEntity;
import com.p120189311.theendoflimbo.entity.custom.TrappedGammaRayProjectileEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<EntityType<AbyssBoatEntity>> MOD_BOAT =
            ENTITY_TYPES.register("mod_boat", () -> EntityType.Builder.<AbyssBoatEntity>of(AbyssBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_boat"));
    public static final RegistryObject<EntityType<AbyssChestBoatEntity>> MOD_CHEST_BOAT =
            ENTITY_TYPES.register("mod_chest_boat", () -> EntityType.Builder.<AbyssChestBoatEntity>of(com.p120189311.theendoflimbo.entity.custom.AbyssChestBoatEntity::new, MobCategory.MISC)
                    .sized(1.375f, 0.5625f).build("mod_chest_boat"));

    public static final RegistryObject<EntityType<TrappedGammaRayProjectileEntity>> TRAPPED_GAMMA_RAY =
            ENTITY_TYPES.register("trapped_gamma_ray", () -> EntityType.Builder.<TrappedGammaRayProjectileEntity>of(TrappedGammaRayProjectileEntity::new, MobCategory.MISC)
                    .sized(0.5f, 0.5f).build("trapped_gamma_ray"));


    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
