package com.example.examplemod.entity.custom;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.effect.ModEffects;
import com.example.examplemod.entity.ModEntities;
import com.example.examplemod.item.ModItems;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.vehicle.AbstractMinecart;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;

public class TrappedGammaRayProjectileEntity extends ThrowableItemProjectile {
    public TrappedGammaRayProjectileEntity(EntityType<? extends ThrowableItemProjectile> p_37442_, Level p_37443_) {
        super(p_37442_, p_37443_);
    }


    public TrappedGammaRayProjectileEntity(Level pLevel) {
        super(ModEntities.TRAPPED_GAMMA_RAY.get(), pLevel);
    }


    public TrappedGammaRayProjectileEntity(Level pLevel, LivingEntity livingEntity) {
        super(ModEntities.TRAPPED_GAMMA_RAY.get(), livingEntity, pLevel);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.TRAPPED_GAMMA_RAY.get();
    }

    @Override
    protected void onHitEntity(EntityHitResult entityHitResult) {
        super.onHitEntity(entityHitResult);

        var target = entityHitResult.getEntity();

        if (target instanceof AbstractMinecart) {
            this.discard();
        } else if (target instanceof LivingEntity living) {
                living.addEffect(new MobEffectInstance(
                        ModEffects.DEMENTIA.get(),
                        Integer.MAX_VALUE, // effectively infinite
                        0, // amplifier
                        false, // ambient
                        false, // showParticles
                        true  // showIcon
                    ));

                living.level().playSound(
                    null,
                    living.getX(), living.getY(), living.getZ(),
                    SoundEvents.GLASS_BREAK,
                    SoundSource.PLAYERS,
                    1.0F,
                    1.5F + living.level().random.nextFloat() * 0.5F
                );
        }
        this.discard();
    }

    @Override
    protected void onHitBlock(BlockHitResult hitResult) {
        BlockState blockState = level().getBlockState(hitResult.getBlockPos());

        if (isPassThrough(blockState)) {
            return;
        }

        super.onHitBlock(hitResult);
        this.discard();
    }

    private boolean isPassThrough(BlockState state) {
        return !(
                // Stone & natural rock types
                        state.is(BlockTags.BASE_STONE_OVERWORLD) ||
                        state.is(BlockTags.BASE_STONE_NETHER) ||
                        state.is(BlockTags.STONE_BRICKS) ||
                        state.is(BlockTags.WALLS) ||
                        state.is(BlockTags.STONE_ORE_REPLACEABLES) ||
                        state.is(BlockTags.COAL_ORES) ||
                        state.is(BlockTags.IRON_ORES) ||
                        state.is(BlockTags.COPPER_ORES) ||
                        state.is(BlockTags.GOLD_ORES) ||
                        state.is(BlockTags.DIAMOND_ORES) ||
                        state.is(BlockTags.EMERALD_ORES) ||
                        state.is(BlockTags.LAPIS_ORES) ||
                        state.is(BlockTags.REDSTONE_ORES) ||
                                state.is(Blocks.ANCIENT_DEBRIS) ||
                                state.is(Blocks.COAL_BLOCK) ||
                                state.is(Blocks.IRON_BLOCK) ||
                                state.is(Blocks.RAW_IRON_BLOCK) ||
                                state.is(Blocks.COPPER_BLOCK) ||
                                state.is(Blocks.RAW_COPPER_BLOCK) ||
                                state.is(Blocks.GOLD_BLOCK) ||
                                state.is(Blocks.RAW_GOLD_BLOCK) ||
                                state.is(Blocks.DIAMOND_BLOCK) ||
                                state.is(Blocks.REDSTONE_BLOCK) ||
                                state.is(Blocks.LAPIS_BLOCK) ||
                                state.is(Blocks.EMERALD_BLOCK) ||
                                state.is(Blocks.AMETHYST_BLOCK) ||
                                state.is(Blocks.BUDDING_AMETHYST) ||
                                state.is(Blocks.NETHERITE_BLOCK) ||
                                state.is(Blocks.WARPED_NYLIUM) ||
                                state.is(Blocks.CRIMSON_NYLIUM) ||
                                state.is(Blocks.STONE_STAIRS) ||
                                state.is(Blocks.STONE_SLAB) ||
                                state.is(Blocks.CRACKED_STONE_BRICKS) ||
                                state.is(Blocks.STONE_BRICK_STAIRS) ||
                                state.is(Blocks.STONE_BRICK_SLAB) ||
                                state.is(Blocks.CHISELED_STONE_BRICKS) ||
                                state.is(Blocks.MOSSY_COBBLESTONE_STAIRS) ||
                                state.is(Blocks.MOSSY_COBBLESTONE_SLAB) ||
                                state.is(Blocks.SMOOTH_STONE) ||
                                state.is(Blocks.SMOOTH_STONE_SLAB) ||
                                state.is(Blocks.COBBLESTONE) ||
                                state.is(Blocks.COBBLESTONE_STAIRS) ||
                                state.is(Blocks.COBBLESTONE_SLAB) ||
                                state.is(Blocks.MOSSY_COBBLESTONE) ||
                                state.is(Blocks.MOSSY_COBBLESTONE_STAIRS) ||
                                state.is(Blocks.MOSSY_COBBLESTONE_SLAB) ||
                                state.is(Blocks.ANDESITE) ||
                                state.is(Blocks.ANDESITE_STAIRS) ||
                                state.is(Blocks.ANDESITE_SLAB) ||
                                state.is(Blocks.POLISHED_ANDESITE) ||
                                state.is(Blocks.POLISHED_ANDESITE_STAIRS) ||
                                state.is(Blocks.POLISHED_ANDESITE_SLAB) ||
                                state.is(Blocks.DIORITE) ||
                                state.is(Blocks.DIORITE_STAIRS) ||
                                state.is(Blocks.DIORITE_SLAB) ||
                                state.is(Blocks.POLISHED_DIORITE_STAIRS) ||
                                state.is(Blocks.POLISHED_DIORITE_SLAB) ||
                                state.is(Blocks.GRANITE) ||
                                state.is(Blocks.GRANITE_STAIRS) ||
                                state.is(Blocks.GRANITE_SLAB) ||
                                state.is(Blocks.POLISHED_GRANITE_STAIRS) ||
                                state.is(Blocks.POLISHED_GRANITE_SLAB) ||
                                state.is(Blocks.DEEPSLATE) ||
                                state.is(Blocks.CHISELED_DEEPSLATE) ||
                                state.is(Blocks.COBBLED_DEEPSLATE) ||
                                state.is(Blocks.COBBLED_DEEPSLATE_STAIRS) ||
                                state.is(Blocks.COBBLED_DEEPSLATE_SLAB) ||
                                state.is(Blocks.POLISHED_DEEPSLATE) ||
                                state.is(Blocks.POLISHED_DEEPSLATE_STAIRS) ||
                                state.is(Blocks.POLISHED_DEEPSLATE_SLAB) ||
                                state.is(Blocks.DEEPSLATE_BRICKS) ||
                                state.is(Blocks.CRACKED_DEEPSLATE_BRICKS) ||
                                state.is(Blocks.DEEPSLATE_BRICK_STAIRS) ||
                                state.is(Blocks.DEEPSLATE_BRICK_SLAB) ||
                                state.is(Blocks.DEEPSLATE_TILES) ||
                                state.is(Blocks.CRACKED_DEEPSLATE_TILES) ||
                                state.is(Blocks.DEEPSLATE_TILE_STAIRS) ||
                                state.is(Blocks.DEEPSLATE_TILE_SLAB) ||
                                state.is(Blocks.REINFORCED_DEEPSLATE) ||
                                state.is(Blocks.BRICKS) ||
                                state.is(Blocks.BRICK_STAIRS) ||
                                state.is(Blocks.BRICK_SLAB) ||
                                state.is(Blocks.NETHER_BRICKS) ||
                                state.is(Blocks.CRACKED_NETHER_BRICKS) ||
                                state.is(Blocks.NETHER_BRICK_STAIRS) ||
                                state.is(Blocks.NETHER_BRICK_SLAB) ||
                                state.is(Blocks.CHISELED_NETHER_BRICKS) ||
                                state.is(Blocks.NETHER_BRICK_FENCE) ||
                                state.is(Blocks.RED_NETHER_BRICKS) ||
                                state.is(Blocks.RED_NETHER_BRICK_STAIRS) ||
                                state.is(Blocks.RED_NETHER_BRICK_SLAB) ||
                                state.is(Blocks.TUFF) ||
                                state.is(Blocks.BASALT) ||
                                state.is(Blocks.POLISHED_BASALT) ||
                                state.is(Blocks.SMOOTH_BASALT) ||
                                state.is(Blocks.BLACKSTONE) ||
                                state.is(Blocks.GILDED_BLACKSTONE) ||
                                state.is(Blocks.BLACKSTONE_STAIRS) ||
                                state.is(Blocks.BLACKSTONE_SLAB) ||
                                state.is(Blocks.POLISHED_BLACKSTONE) ||
                                state.is(Blocks.CHISELED_POLISHED_BLACKSTONE) ||
                                state.is(Blocks.POLISHED_BLACKSTONE_STAIRS) ||
                                state.is(Blocks.POLISHED_BLACKSTONE_SLAB) ||
                                state.is(Blocks.POLISHED_BLACKSTONE_BRICKS) ||
                                state.is(Blocks.CRACKED_POLISHED_BLACKSTONE_BRICKS) ||
                                state.is(Blocks.POLISHED_BLACKSTONE_BRICK_STAIRS) ||
                                state.is(Blocks.POLISHED_BLACKSTONE_BRICK_SLAB) ||
                                state.is(Blocks.DRIPSTONE_BLOCK) ||
                                state.is(Blocks.BEDROCK) ||
                                state.is(Blocks.SANDSTONE) ||
                                state.is(Blocks.SANDSTONE_STAIRS) ||
                                state.is(Blocks.SANDSTONE_SLAB) ||
                                state.is(Blocks.SANDSTONE_WALL) ||
                                state.is(Blocks.SMOOTH_SANDSTONE) ||
                                state.is(Blocks.SMOOTH_SANDSTONE_STAIRS) ||
                                state.is(Blocks.SMOOTH_SANDSTONE_SLAB) ||
                                state.is(Blocks.CHISELED_SANDSTONE) ||
                                state.is(Blocks.CUT_SANDSTONE) ||
                                state.is(Blocks.CUT_SANDSTONE_SLAB) ||
                                state.is(Blocks.RED_SANDSTONE) ||
                                state.is(Blocks.RED_SANDSTONE_STAIRS) ||
                                state.is(Blocks.RED_SANDSTONE_SLAB) ||
                                state.is(Blocks.RED_SANDSTONE_WALL) ||
                                state.is(Blocks.SMOOTH_RED_SANDSTONE) ||
                                state.is(Blocks.SMOOTH_RED_SANDSTONE_STAIRS) ||
                                state.is(Blocks.SMOOTH_RED_SANDSTONE_SLAB) ||
                                state.is(Blocks.CHISELED_RED_SANDSTONE) ||
                                state.is(Blocks.CUT_RED_SANDSTONE) ||
                                state.is(Blocks.CUT_RED_SANDSTONE_SLAB) ||
                                state.is(Blocks.PRISMARINE) ||
                                state.is(Blocks.PRISMARINE_STAIRS) ||
                                state.is(Blocks.PRISMARINE_SLAB) ||
                                state.is(Blocks.PRISMARINE_WALL) ||
                                state.is(Blocks.PRISMARINE_BRICKS) ||
                                state.is(Blocks.PRISMARINE_BRICK_STAIRS) ||
                                state.is(Blocks.PRISMARINE_BRICK_SLAB) ||
                                state.is(Blocks.DARK_PRISMARINE) ||
                                state.is(Blocks.DARK_PRISMARINE_STAIRS) ||
                                state.is(Blocks.DARK_PRISMARINE_SLAB) ||
                                state.is(Blocks.SEA_LANTERN) ||
                                state.is(Blocks.MAGMA_BLOCK) ||
                                state.is(Blocks.OBSIDIAN) ||
                                state.is(Blocks.CRYING_OBSIDIAN) ||
                                state.is(Blocks.END_STONE) ||
                                state.is(Blocks.END_STONE_BRICK_SLAB) ||
                                state.is(Blocks.END_STONE_BRICK_STAIRS) ||
                                state.is(Blocks.END_STONE_BRICKS) ||
                                state.is(Blocks.PURPUR_BLOCK) ||
                                state.is(Blocks.PURPUR_PILLAR) ||
                                state.is(Blocks.PURPUR_STAIRS) ||
                                state.is(Blocks.PURPUR_SLAB) ||
                                state.is(Blocks.GLOWSTONE) ||
                                state.is(Blocks.NETHER_QUARTZ_ORE) ||
                                state.is(Blocks.QUARTZ_BLOCK) ||
                                state.is(Blocks.QUARTZ_STAIRS) ||
                                state.is(Blocks.QUARTZ_SLAB) ||
                                state.is(Blocks.CHISELED_QUARTZ_BLOCK) ||
                                state.is(Blocks.QUARTZ_BRICKS) ||
                                state.is(Blocks.QUARTZ_PILLAR) ||
                                state.is(Blocks.SMOOTH_QUARTZ) ||
                                state.is(Blocks.SMOOTH_QUARTZ_STAIRS) ||
                                state.is(Blocks.SMOOTH_QUARTZ_SLAB) ||
                                state.is(Blocks.EXPOSED_COPPER) ||
                                state.is(Blocks.WEATHERED_COPPER) ||
                                state.is(Blocks.OXIDIZED_COPPER) ||
                                state.is(Blocks.CUT_COPPER) ||
                                state.is(Blocks.EXPOSED_CUT_COPPER) ||
                                state.is(Blocks.WEATHERED_CUT_COPPER) ||
                                state.is(Blocks.OXIDIZED_CUT_COPPER) ||
                                state.is(Blocks.CUT_COPPER_STAIRS) ||
                                state.is(Blocks.EXPOSED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.WEATHERED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.OXIDIZED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.CUT_COPPER_SLAB) ||
                                state.is(Blocks.EXPOSED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.WEATHERED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.OXIDIZED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.WAXED_COPPER_BLOCK) ||
                                state.is(Blocks.WAXED_EXPOSED_COPPER) ||
                                state.is(Blocks.WAXED_WEATHERED_COPPER) ||
                                state.is(Blocks.WAXED_OXIDIZED_COPPER) ||
                                state.is(Blocks.WAXED_CUT_COPPER) ||
                                state.is(Blocks.WAXED_EXPOSED_CUT_COPPER) ||
                                state.is(Blocks.WAXED_WEATHERED_CUT_COPPER) ||
                                state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER) ||
                                state.is(Blocks.WAXED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.WAXED_EXPOSED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.WAXED_WEATHERED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER_STAIRS) ||
                                state.is(Blocks.WAXED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.WAXED_EXPOSED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.WAXED_WEATHERED_CUT_COPPER_SLAB) ||
                                state.is(Blocks.WAXED_OXIDIZED_CUT_COPPER_SLAB) ||

                                // Util Blocks Made of Stones
                                state.is(Blocks.FURNACE) ||
                                state.is(Blocks.BLAST_FURNACE) ||
                                state.is(Blocks.SMOKER) ||
                                state.is(Blocks.SMITHING_TABLE) ||
                                state.is(Blocks.GRINDSTONE) ||
                                state.is(Blocks.STONECUTTER) ||
                                state.is(Blocks.ANVIL) ||
                                state.is(Blocks.CHIPPED_ANVIL) ||
                                state.is(Blocks.DAMAGED_ANVIL) ||
                                state.is(Blocks.ENCHANTING_TABLE) ||
                                state.is(Blocks.CAULDRON) ||
                                state.is(Blocks.WATER_CAULDRON) ||
                                state.is(Blocks.LAVA_CAULDRON) ||
                                state.is(Blocks.POWDER_SNOW_CAULDRON) ||
                                state.is(Blocks.BELL) ||
                                state.is(Blocks.BEACON) ||
                                state.is(Blocks.LODESTONE) ||
                                state.is(Blocks.ENDER_CHEST) ||
                                state.is(Blocks.RESPAWN_ANCHOR) ||
                                state.is(Blocks.END_PORTAL_FRAME) ||
                                state.is(Blocks.INFESTED_STONE) ||
                                state.is(Blocks.INFESTED_COBBLESTONE) ||
                                state.is(Blocks.INFESTED_STONE_BRICKS) ||
                                state.is(Blocks.INFESTED_MOSSY_STONE_BRICKS) ||
                                state.is(Blocks.INFESTED_CRACKED_STONE_BRICKS) ||
                                state.is(Blocks.INFESTED_CHISELED_STONE_BRICKS) ||
                                state.is(Blocks.INFESTED_DEEPSLATE) ||
                                state.is(Blocks.LAVA) ||
                                state.is(Blocks.PISTON) ||
                                state.is(Blocks.STICKY_PISTON) ||
                                state.is(Blocks.MOVING_PISTON) ||
                                state.is(Blocks.HOPPER) ||
                                state.is(Blocks.DISPENSER) ||
                                state.is(Blocks.DROPPER) ||
                                state.is(Blocks.OBSERVER) ||
                                state.is(Blocks.JIGSAW) ||
                                state.is(Blocks.COMMAND_BLOCK) ||
                                state.is(Blocks.CHAIN_COMMAND_BLOCK) ||
                                state.is(Blocks.REPEATING_COMMAND_BLOCK) ||

                                // Concrete & Concrete Powder
                                state.is(Blocks.WHITE_CONCRETE) || state.is(Blocks.WHITE_CONCRETE_POWDER) ||
                                state.is(Blocks.ORANGE_CONCRETE) || state.is(Blocks.ORANGE_CONCRETE_POWDER) ||
                                state.is(Blocks.MAGENTA_CONCRETE) || state.is(Blocks.MAGENTA_CONCRETE_POWDER) ||
                                state.is(Blocks.LIGHT_BLUE_CONCRETE) || state.is(Blocks.LIGHT_BLUE_CONCRETE_POWDER) ||
                                state.is(Blocks.YELLOW_CONCRETE) || state.is(Blocks.YELLOW_CONCRETE_POWDER) ||
                                state.is(Blocks.LIME_CONCRETE) || state.is(Blocks.LIME_CONCRETE_POWDER) ||
                                state.is(Blocks.PINK_CONCRETE) || state.is(Blocks.PINK_CONCRETE_POWDER) ||
                                state.is(Blocks.GRAY_CONCRETE) || state.is(Blocks.GRAY_CONCRETE_POWDER) ||
                                state.is(Blocks.LIGHT_GRAY_CONCRETE) || state.is(Blocks.LIGHT_GRAY_CONCRETE_POWDER) ||
                                state.is(Blocks.CYAN_CONCRETE) || state.is(Blocks.CYAN_CONCRETE_POWDER) ||
                                state.is(Blocks.PURPLE_CONCRETE) || state.is(Blocks.PURPLE_CONCRETE_POWDER) ||
                                state.is(Blocks.BLUE_CONCRETE) || state.is(Blocks.BLUE_CONCRETE_POWDER) ||
                                state.is(Blocks.BROWN_CONCRETE) || state.is(Blocks.BROWN_CONCRETE_POWDER) ||
                                state.is(Blocks.GREEN_CONCRETE) || state.is(Blocks.GREEN_CONCRETE_POWDER) ||
                                state.is(Blocks.RED_CONCRETE) || state.is(Blocks.RED_CONCRETE_POWDER) ||
                                state.is(Blocks.BLACK_CONCRETE) || state.is(Blocks.BLACK_CONCRETE_POWDER) ||

                                // Custom Blocks
                                state.is(ModBlocks.ABSOLUTE_BLACK_ORE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK_STAIRS.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK_SLAB.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK_FENCE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_BLACK_WALL.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_ORE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_STAIRS.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_SLAB.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_FENCE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get()) ||
                                state.is(ModBlocks.ABSOLUTE_WHITE_WALL.get()) ||
                                state.is(ModBlocks.JUMPY_BLOCK.get())

        );
    }
}
