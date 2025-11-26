package com.p120189311.theendoflimbo.block.entity;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TheEndOfLimboBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TheEndOfLimboMod.MAIN);

    public static final RegistryObject<BlockEntityType<MysteryStandBlockEntity>> MYSTERY_STAND_BE =
            BLOCK_ENTITIES.register("mystery_stand_be", () ->
                    BlockEntityType.Builder.of(MysteryStandBlockEntity::new,
                            TheEndOfLimboBlocks.MYSTERY_STAND.get()).build(null));

    public static final RegistryObject<BlockEntityType<TheEndOfLimboSignBlockEntity>> MOD_SIGN =
            BLOCK_ENTITIES.register("mod_sign", () ->
                    BlockEntityType.Builder.of(TheEndOfLimboSignBlockEntity::new,
                            TheEndOfLimboBlocks.ABYSS_SIGN.get(), TheEndOfLimboBlocks.ABYSS_WALL_SIGN.get()).build(null));

    public static final RegistryObject<BlockEntityType<TheEndOfLimboHangingSignBlockEntity>> MOD_HANGING_SIGN =
            BLOCK_ENTITIES.register("mod_hanging_sign", () ->
                    BlockEntityType.Builder.of(TheEndOfLimboHangingSignBlockEntity::new,
                            TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get(), TheEndOfLimboBlocks.ABYSS_WALL_HANGING_SIGN.get()).build(null));

    public static void register (IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}
