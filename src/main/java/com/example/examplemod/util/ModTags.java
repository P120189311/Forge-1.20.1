package com.example.examplemod.util;

import com.example.examplemod.ExampleMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DOWSING_ROD_VALUABLES = tag("dowsing_rod_valuables");
        public static final TagKey<Block> NEEDS_ABSOLUTE_BLACK_TOOL = tag("needs_absolute_black_tool");
        public static final TagKey<Block> NEEDS_ABSOLUTE_WHITE_TOOL = tag("needs_absolute_white_tool");
        public static final TagKey<Block> ABYSS_LOGS = tag("abyss_logs");
        public static final TagKey<Block> MOD_PLANKS = tag("mod_planks");
        public static final TagKey<Block> REPLACEABLE_PLANTLIKE = tag("replaceable_plantlike");

        private static TagKey<Block> tag(String name) {
            return BlockTags.create(new ResourceLocation(ExampleMod.MOD_ID, name));
        }

        private static TagKey<Block> forgeTag(String name) {
            return BlockTags.create(new ResourceLocation("forge", name));
        }
    }

    public static class Items {
        public static final TagKey<Item> BLACKY_GEMS = forgeTag("gems/blacky");
        public static final TagKey<Item> WHITY_GEMS = forgeTag("gems/whity");
        public static final TagKey<Item> ABYSS_LOGS = tag("abyss_logs");
        public static final TagKey<Item> MOD_PLANKS = tag("mod_planks");
        public static final TagKey<Item> PLANKS = tag("planks");

        private static TagKey<Item> tag(String name) {
            return ItemTags.create(new ResourceLocation(ExampleMod.MOD_ID, name));
        }

        private static TagKey<Item> forgeTag(String name) {
            return ItemTags.create(new ResourceLocation("forge", name));
        }
    }
}