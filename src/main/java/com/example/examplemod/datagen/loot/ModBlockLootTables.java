package com.example.examplemod.datagen.loot;

import com.example.examplemod.block.ModBlocks;
import com.example.examplemod.block.custom.BlueHillTomatoCropBlock;
import com.example.examplemod.item.ModItems;
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
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraftforge.registries.RegistryObject;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        //Black Blocks
        dropSelf(ModBlocks.ABSOLUTE_BLACK.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_STAIRS.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_SLAB.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_FENCE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_FENCE_GATE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_WALL.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_BUTTON.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ABSOLUTE_BLACK_TRAPDOOR.get());

        //White Blocks
        dropSelf(ModBlocks.ABSOLUTE_WHITE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_STAIRS.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_SLAB.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_FENCE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_FENCE_GATE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_WALL.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_BUTTON.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_PRESSURE_PLATE.get());
        dropSelf(ModBlocks.ABSOLUTE_WHITE_TRAPDOOR.get());

        //Misc Blocks
        dropSelf(ModBlocks.JUMPY_BLOCK.get());
        dropSelf(ModBlocks.MYSTERY_STAND.get());

        //Plants
        this.dropSelf(ModBlocks.ABYSS_LOG.get());
        this.dropSelf(ModBlocks.ABYSS_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ABYSS_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ABYSS_WOOD.get());
        this.dropSelf(ModBlocks.ABYSS_PLANKS.get());
        this.dropSelf(ModBlocks.ABYSS_PLANKS_CURSED.get());
        this.dropSelf(ModBlocks.ABYSS_SAPLING.get());

        this.add(ModBlocks.ABYSS_LEAVES.get(), block ->
                LootTable.lootTable()
                        // Pool 1: saplings (vanilla chance)
                        .withPool(
                                LootPool.lootPool()
                                        .setRolls(ConstantValue.exactly(1))
                                        .add(
                                                LootItem.lootTableItem(ModBlocks.ABYSS_SAPLING.get())
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
                                                LootItem.lootTableItem(ModItems.NOCTURNAL_DWIGHT.get())
                                                        .when(LootItemRandomChanceCondition.randomChance(0.01f))
                                                        .apply(ApplyBonusCount.addUniformBonusCount(Enchantments.BLOCK_FORTUNE))
                                        )
                        )
        );

        this.add(ModBlocks.ABYSS_SIGN.get(), block ->
                createSingleItemTable(ModItems.ABYSS_SIGN.get()));
        this.add(ModBlocks.ABYSS_WALL_SIGN.get(), block ->
                createSingleItemTable(ModItems.ABYSS_SIGN.get()));
        this.add(ModBlocks.ABYSS_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.ABYSS_HANGING_SIGN.get()));
        this.add(ModBlocks.ABYSS_WALL_HANGING_SIGN.get(), block ->
                createSingleItemTable(ModItems.ABYSS_HANGING_SIGN.get()));

        // Flowers
        this.dropSelf(ModBlocks.DECAYING_HARMONY.get());
        this.add(ModBlocks.POTTED_DECAYING_HARMONY.get(), createPotFlowerItemTable(ModBlocks.DECAYING_HARMONY.get()));
        this.add(ModBlocks.POTTED_ABYSS_SAPLING.get(), createPotFlowerItemTable(ModBlocks.ABYSS_SAPLING.get()));

        add(ModBlocks.ABSOLUTE_BLACK_ORE.get(),
                (block -> createOreDrop(ModBlocks.ABSOLUTE_BLACK_ORE.get(), ModItems.BLACKY.get())));
        add(ModBlocks.ABSOLUTE_WHITE_ORE.get(),
                (block -> createOreDrop(ModBlocks.ABSOLUTE_WHITE_ORE.get(), ModItems.WHITY.get())));
        add(ModBlocks.ABSOLUTE_BLACK_DOOR.get(), createDoorTable(ModBlocks.ABSOLUTE_BLACK_DOOR.get()));
        add(ModBlocks.ABSOLUTE_WHITE_DOOR.get(), createDoorTable(ModBlocks.ABSOLUTE_WHITE_DOOR.get()));

        LootItemCondition.Builder lootitemcondition$builder = LootItemBlockStatePropertyCondition
                .hasBlockStateProperties(ModBlocks.BLUE_HILL_TOMATO_CROP.get())
                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(BlueHillTomatoCropBlock.AGE, 4));

        this.add(ModBlocks.BLUE_HILL_TOMATO_CROP.get(), createCropDrops(ModBlocks.BLUE_HILL_TOMATO_CROP.get(), ModItems.BLUE_HILL_TOMATO.get(),
                ModItems.BLUE_HILL_TOMATO_SEEDS.get(), lootitemcondition$builder));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;
    }
}
