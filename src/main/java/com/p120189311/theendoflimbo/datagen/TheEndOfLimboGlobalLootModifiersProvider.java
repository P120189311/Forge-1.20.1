package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.loot.AddItemModifier;
import com.p120189311.theendoflimbo.loot.AddSusSandItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class TheEndOfLimboGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public TheEndOfLimboGlobalLootModifiersProvider(PackOutput output) {
        super(output, TheEndOfLimboMod.MAIN);
    }

    @Override
    protected void start() {
        add("mystery_stand_from_witch", new AddItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("entities/witch")).build(),
                LootItemRandomChanceCondition.randomChance(0.2f).build()}, TheEndOfLimboBlocks.MYSTERY_STAND.get().asItem()));

        add("dowsing_rod_from_suspicious_sand", new AddSusSandItemModifier(new LootItemCondition[]{
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build(),}, TheEndOfLimboItems.DOWSING_ROD.get()));
    }
}
