package com.p120189311.theendoflimbo.datagen.loot;

import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.block.custom.BlueHillTomatoCropBlock;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class TheEndOfLimboBlockLootTables extends BlockLootSubProvider {
    public TheEndOfLimboBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Black Blocks
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_STAIRS.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_SLAB.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_FENCE_GATE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_WALL.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_BUTTON.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_BLACK_TRAPDOOR.get());

        //White Blocks
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_STAIRS.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_SLAB.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_WALL.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_BUTTON.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get());
        dropSelf(TheEndOfLimboBlocks.ABSOLUTE_WHITE_TRAPDOOR.get());

        //Misc Blocks
        dropSelf(TheEndOfLimboBlocks.JUMPY_BLOCK.get());
        dropSelf(TheEndOfLimboBlocks.MYSTERY_STAND.get());

        //Plants
        this.dropSelf(TheEndOfLimboBlocks.ABYSS_LOG.get());
        this.dropSelf(TheEndOfLimboBlocks.ABYSS_WOOD.get());
        this.dropSelf(TheEndOfLimboBlocks.STRIPPED_ABYSS_LOG.get());
        this.dropSelf(TheEndOfLimboBlocks.STRIPPED_ABYSS_WOOD.get());
        this.dropSelf(TheEndOfLimboBlocks.ABYSS_PLANKS.get());
        this.dropSelf(TheEndOfLimboBlocks.ABYSS_PLANKS_CURSED.get());
        this.dropSelf(TheEndOfLimboBlocks.ABYSS_SAPLING.get());

        this.add(TheEndOfLimboBlocks.ABYSS_LEAVES.get(), block ->
                LootTable.lootTable()
                        // Pool 1: saplings (vanilla chance)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(TheEndOfLimboBlocks.ABYSS_SAPLING.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                        )
                        )
                        // Pool 2: sticks (vanilla chance)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(Items.STICK)
                                                        .when(LootItemRandomChanceCondition.randomChance(0.05f))
                                        )
                        )
                        // Pool 3: rare diamond drop (custom)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(TheEndOfLimboItems.NOCTURNAL_DWIGHT.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                        )
                        )
        );

        this.add(TheEndOfLimboBlocks.ABYSS_SIGN.get(), block ->
                createSingleItemTable(TheEndOfLimboItems.ABYSS_SIGN.get()));
        this.add(TheEndOfLimboBlocks.ABYSS_WALL_SIGN.get(), block ->
                createSingleItemTable(TheEndOfLimboItems.ABYSS_SIGN.get()));
        this.add(TheEndOfLimboBlocks.ABYSS_HANGING_SIGN.get(), block ->
                createSingleItemTable(TheEndOfLimboItems.ABYSS_HANGING_SIGN.get()));
        this.add(TheEndOfLimboBlocks.ABYSS_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(TheEndOfLimboItems.ABYSS_HANGING_SIGN.get()));

        // Flowers
        this.dropSelf(TheEndOfLimboBlocks.DECAYING_HARMONY.get());
        this.add(TheEndOfLimboBlocks.POTTED_DECAYING_HARMONY.get(), createPotFlowerItemTable(TheEndOfLimboBlocks.DECAYING_HARMONY.get()));
        this.add(TheEndOfLimboBlocks.POTTED_ABYSS_SAPLING.get(), createPotFlowerItemTable(TheEndOfLimboBlocks.ABYSS_SAPLING.get()));

        add(TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get(),
                (block -> createOreDrop(TheEndOfLimboBlocks.ABSOLUTE_BLACK_ORE.get(), TheEndOfLimboItems.BLACKY.get())));
        add(TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get(),
                (block -> createOreDrop(TheEndOfLimboBlocks.ABSOLUTE_WHITE_ORE.get(), TheEndOfLimboItems.WHITY.get())));
        add(TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get(), createDoorTable(TheEndOfLimboBlocks.ABSOLUTE_BLACK_DOOR.get()));
        add(TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get(), createDoorTable(TheEndOfLimboBlocks.ABSOLUTE_WHITE_DOOR.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(TheEndOfLimboBlocks.BLUE_HILL_TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueHillTomatoCropBlock.AGE, 4));

        this.add(TheEndOfLimboBlocks.BLUE_HILL_TOMATO_CROP.get(), createCropDrops(TheEndOfLimboBlocks.BLUE_HILL_TOMATO_CROP.get(), TheEndOfLimboItems.BLUE_HILL_TOMATO.get(),
                TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return TheEndOfLimboBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
