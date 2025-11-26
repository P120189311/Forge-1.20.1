package com.p120189311.theendoflimbo.event;

import com.p120189311.theendoflimbo.TheEndOfLimboMod;
import com.p120189311.theendoflimbo.item.TheEndOfLimboItems;
import com.p120189311.theendoflimbo.villager.TheEndOfLimboVillagers;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = TheEndOfLimboMod.MAIN)
public class TheEndOfLimboEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {

        // Numbers are as follows: Count, Count, MaxUses, Xp, Price Multiplier

        if(event.getType() == VillagerProfession.FARMER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 3),
                    new ItemStack(TheEndOfLimboItems.BLUE_HILL_TOMATO.get(), 6),
                    10,8,0.02f));

            // Level 5
            trades.get(5).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.DIAMOND, 67),
                    new ItemStack(TheEndOfLimboItems.VOID_OF_KNOWLEDGE.get(), 1),
                    5,50,0.08f));
        }

        if(event.getType() == VillagerProfession.ARMORER) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 4
            trades.get(4).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(Items.EMERALD, 67),
                    new ItemStack(TheEndOfLimboItems.ABSOLUTE_BLACK_SWORD.get(), 1),
                    1,30,0.1f));
        }

        if(event.getType() == TheEndOfLimboVillagers.OVERSEER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            // Level 1
            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(TheEndOfLimboItems.BLACKY.get(), 64),
                    new ItemStack(TheEndOfLimboItems.VOID_OF_KNOWLEDGE.get(), 1),
                    5,12,0.05f));

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(TheEndOfLimboItems.WHITY.get(), 64),
                    new ItemStack(TheEndOfLimboItems.ETERNAL_FLAME.get(), 1),
                    5,12,0.05f));
        }
    }

    @SubscribeEvent
    public static void addCustomWanderingTrades(WandererTradesEvent event) {
        List<VillagerTrades.ItemListing> genericTrades = event.getGenericTrades();
        List<VillagerTrades.ItemListing> rareTrades = event.getRareTrades();

        genericTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 6),
                new ItemStack(TheEndOfLimboItems.BLUE_HILL_TOMATO_SEEDS.get(), 6),
                5,3,0.05f));

        rareTrades.add((pTrader, pRandom) -> new MerchantOffer(
                new ItemStack(Items.EMERALD, 30),
                new ItemStack(TheEndOfLimboItems.DOWSING_ROD.get(), 1),
                4,8,0.08f));
    }
}
