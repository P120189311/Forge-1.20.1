package com.p120189311.theendoflimbo.item;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.LazyLoadedValue;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Supplier;

public enum TheEndOfLimboArmorMaterials implements ArmorMaterial {
    ABSOLUTE_BLACK("absolute_black", 35, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, () -> Ingredient.of(TheEndOfLimboItems.BLACKY.get())),
    ABSOLUTE_WHITE("absolute_white", 35, new int[]{2, 5, 7, 2}, 5, SoundEvents.ARMOR_EQUIP_CHAIN, 1.0F, 0.0F, () -> Ingredient.of(TheEndOfLimboItems.WHITY.get()));

    private static final int[] HEALTH_PER_SLOT = new int[]{13, 15, 16, 11};
    private final String name;
    private final int durabilityMultiplier;
    private final int[] slotProtections;
    private final int enchantmentValue;
    private final SoundEvent sound;
    private final float toughness;
    private final float knockbackResistance;
    private final LazyLoadedValue<Ingredient> repairIngredient;

    TheEndOfLimboArmorMaterials(String p_40474_, int p_40475_, int[] p_40476_, int p_40477_, SoundEvent p_40478_, float p_40479_, float p_40480_, Supplier<Ingredient> p_40481_) {
        this.name = p_40474_;
        this.durabilityMultiplier = p_40475_;
        this.slotProtections = p_40476_;
        this.enchantmentValue = p_40477_;
        this.sound = p_40478_;
        this.toughness = p_40479_;
        this.knockbackResistance = p_40480_;
        this.repairIngredient = new LazyLoadedValue(p_40481_);
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> 13 * this.durabilityMultiplier;
            case CHESTPLATE -> 15 * this.durabilityMultiplier;
            case LEGGINGS -> 16 * this.durabilityMultiplier;
            case BOOTS -> 11 * this.durabilityMultiplier;
        };
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return switch (type) {
            case HELMET -> this.slotProtections[0];
            case CHESTPLATE -> this.slotProtections[1];
            case LEGGINGS -> this.slotProtections[2];
            case BOOTS -> this.slotProtections[3];
        };
    }

    public int getEnchantmentValue() {
        return this.enchantmentValue;
    }

    public SoundEvent getEquipSound() {
        return this.sound;
    }

    public Ingredient getRepairIngredient() {
        return (Ingredient)this.repairIngredient.get();
    }

    public String getName() {

        return TheEndOfLimboMod.MAIN + ":" + this.name;
    }

    public float getToughness() {
        return this.toughness;
    }

    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }
}