package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.datagen.loot.TheEndOfLimboBlockLootTables;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.List;
import java.util.Set;

public class TheEndOfLimboLootTableProvider {
    public static LootTableProvider create(PackOutput output) {
        return new LootTableProvider(output, Set.of(),
                List.of(new LootTableProvider.SubProviderEntry(TheEndOfLimboBlockLootTables::new, LootContextParamSets.BLOCK)));
    }
}
