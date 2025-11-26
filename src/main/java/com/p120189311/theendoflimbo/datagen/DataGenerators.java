package com.p120189311.theendoflimbo.datagen;


import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.CompletableFuture;

@Mod.EventBusSubscriber(modid = TheEndOfLimboMod.MAIN, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();

        generator.addProvider(event.includeServer(), new TheEndOfLimboRecipeProvider(packOutput));
        generator.addProvider(event.includeServer(), TheEndOfLimboLootTableProvider.create(packOutput));

        generator.addProvider(event.includeClient(), new TheEndOfLimboBlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new TheEndOfLimboItemModelProvider(packOutput, existingFileHelper));

        TheEndOfLimboBlockTagGenerator blockTagGenerator = generator.addProvider(event.includeServer(),
                new TheEndOfLimboBlockTagGenerator(packOutput, lookupProvider, existingFileHelper));
        generator.addProvider(event.includeServer(), new TheEndOfLimboItemTagGenerator(packOutput, lookupProvider, blockTagGenerator.contentsGetter(), existingFileHelper));

        generator.addProvider(event.includeServer(), new TheEndOfLimboGlobalLootModifiersProvider(packOutput));
        generator.addProvider(event.includeServer(), new TheEndOfLimboPoiTypeTagsProvider(packOutput, lookupProvider, existingFileHelper));

        generator.addProvider(event.includeServer(), new TheEndOfLimboWorldGenProvider(packOutput, lookupProvider));
    }
}
