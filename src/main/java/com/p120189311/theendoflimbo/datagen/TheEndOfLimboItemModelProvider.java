package com.p120189311.theendoflimbo.datagen;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.block.TheEndOfLimboBlocks;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.armortrim.TrimMaterial;
import net.minecraft.world.item.armortrim.TrimMaterials;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.LinkedHashMap;

public class TheEndOfLimboItemModelProvider extends ItemModelProvider {
    private static LinkedHashMap<ResourceKey<TrimMaterial>, Float> trimMaterials = new LinkedHashMap<>();
    static {
        trimMaterials.put(TrimMaterials.QUARTZ, 0.1F);
        trimMaterials.put(TrimMaterials.IRON, 0.2F);
        trimMaterials.put(TrimMaterials.NETHERITE, 0.3F);
        trimMaterials.put(TrimMaterials.REDSTONE, 0.4F);
        trimMaterials.put(TrimMaterials.COPPER, 0.5F);
        trimMaterials.put(TrimMaterials.GOLD, 0.6F);
        trimMaterials.put(TrimMaterials.EMERALD, 0.7F);
        trimMaterials.put(TrimMaterials.DIAMOND, 0.8F);
        trimMaterials.put(TrimMaterials.LAPIS, 0.9F);
        trimMaterials.put(TrimMaterials.AMETHYST, 1.0F);
    }

    public TheEndOfLimboItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, TheEndOfLimboMod.MAIN, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        //Simple
        simpleItem(TheEndOfLimboItems.BLACKY);
        simpleItem(TheEndOfLimboItems.WHITY);

        simpleItem(TheEndOfLimboItems.ABYSS_SIGN);
        simpleItem(TheEndOfLimboItems.ABYSS_HANGING_SIGN);

        simpleItem(TheEndOfLimboItems.DOWSING_ROD);

        simpleItem(TheEndOfLimboItems.VOID_OF_KNOWLEDGE);

        simpleItem(TheEndOfLimboItems.ETERNAL_FLAME);
        simpleItem(TheEndOfLimboItems.TRAPPED_GAMMA_RAY);

        simpleItem(TheEndOfLimboItems.SUFFOCATING_WATER_BUCKET);

        simpleItem(TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_BLACK_CHESTPLATE);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_BLACK_LEGGINGS);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_BLACK_BOOTS);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_WHITE_HELMET);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_WHITE_CHESTPLATE);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_WHITE_LEGGINGS);
        simpleItem(TheEndOfLimboItems.ABSOLUTE_WHITE_BOOTS);

        simpleItem(TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS);

        simpleItem(TheEndOfLimboItems.AMBULANCE_BEAT_MUSIC_DISC);
        simpleItem(TheEndOfLimboItems.BAFANGLAICAI_MUSIC_DISC);
        simpleItem(TheEndOfLimboItems.DROP_IT_LIKE_ITS_HOT_MUSIC_DISC);

        simpleItem(TheEndOfLimboItems.ABYSS_BOAT);
        simpleItem(TheEndOfLimboItems.ABYSS_CHEST_BOAT);

        saplingItem(TheEndOfLimboBlocks.ABYSS_SAPLING);

        // Blocks
        simpleBlockItemBlockTexture(TheEndOfLimboBlocks.DECAYING_HARMONY);

        //Handheld
        handheldItem(TheEndOfLimboItems.ABSOLUTE_BLACK_SWORD);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_BLACK_PICKAXE);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_BLACK_SHOVEL);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_BLACK_AXE);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_BLACK_HOE);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_WHITE_SWORD);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_WHITE_PICKAXE);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_WHITE_SHOVEL);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_WHITE_AXE);
        handheldItem(TheEndOfLimboItems.ABSOLUTE_WHITE_HOE);

        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_BLACK_HELMET);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_BLACK_CHESTPLATE);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_BLACK_LEGGINGS);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_BLACK_BOOTS);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_WHITE_HELMET);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_WHITE_CHESTPLATE);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_WHITE_LEGGINGS);
        trimmedArmorItem(TheEndOfLimboItems.ABSOLUTE_WHITE_BOOTS);
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheEndOfLimboMod.MAIN,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(TheEndOfLimboMod.MAIN,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheEndOfLimboMod.MAIN,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleBlockItemBlockTexture(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheEndOfLimboMod.MAIN,"block/" + item.getId().getPath()));
    }

    private ItemModelBuilder saplingItem(RegistryObject<Block> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(TheEndOfLimboMod.MAIN,"block/" + item.getId().getPath()));
    }

    private void trimmedArmorItem(RegistryObject<Item> itemRegistryObject) {
        final String MOD_ID = TheEndOfLimboMod.MAIN; // Change this to your mod id

        if(itemRegistryObject.get() instanceof ArmorItem armorItem) {
            trimMaterials.entrySet().forEach(entry -> {

                ResourceKey<TrimMaterial> trimMaterial = entry.getKey();
                float trimValue = entry.getValue();

                String armorType = switch (armorItem.getEquipmentSlot()) {
                    case HEAD -> "helmet";
                    case CHEST -> "chestplate";
                    case LEGS -> "leggings";
                    case FEET -> "boots";
                    default -> "";
                };

                String armorItemPath = "item/" + armorItem;
                String trimPath = "trims/items/" + armorType + "_trim_" + trimMaterial.location().getPath();
                String currentTrimName = armorItemPath + "_" + trimMaterial.location().getPath() + "_trim";
                ResourceLocation armorItemResLoc = new ResourceLocation(MOD_ID, armorItemPath);
                ResourceLocation trimResLoc = new ResourceLocation(trimPath); // minecraft namespace
                ResourceLocation trimNameResLoc = new ResourceLocation(MOD_ID, currentTrimName);

                // This is used for making the ExistingFileHelper acknowledge that this texture exist, so this will
                // avoid an IllegalArgumentException
                existingFileHelper.trackGenerated(trimResLoc, PackType.CLIENT_RESOURCES, ".png", "textures");

                // Trimmed armorItem files
                getBuilder(currentTrimName)
                        .parent(new ModelFile.UncheckedModelFile("item/generated"))
                        .texture("layer0", armorItemResLoc)
                        .texture("layer1", trimResLoc);

                // Non-trimmed armorItem file (normal variant)
                this.withExistingParent(itemRegistryObject.getId().getPath(),
                                mcLoc("item/generated"))
                        .override()
                        .model(new ModelFile.UncheckedModelFile(trimNameResLoc))
                        .predicate(mcLoc("trim_type"), trimValue).end()
                        .texture("layer0",
                                new ResourceLocation(MOD_ID,
                                        "item/" + itemRegistryObject.getId().getPath()));
            });
        }
    }
}
