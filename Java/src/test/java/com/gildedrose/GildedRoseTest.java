package com.gildedrose;

import static com.gildedrose.constants.ItemType.*;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {


    @Test
    void testStandardBehaviour() {
        Item[] items = new Item[]{
                new Item(STANDARD.getName(), 2, 4)};
        int totalDays = 5;
        updateAndVerifyStandard(items, totalDays);
    }

    @Test
    void testAgedBrieBehaviour() {
        Item[] items = new Item[]{
                new Item("Aged Brie", 0, 0)};
        int totalDays = 60;
        updateAndVerifyBrie(items, totalDays);
    }

    @Test
    void testSulfurasBehaviour() {
        Item[] items = new Item[]{
                new Item("Sulfuras, Hand of Ragnaros", 0, 0)};
        int totalDays = 60;
        updateAndVerifySulfuras(items, totalDays);
    }

    @Test
    void testBackstageBehaviour() {
        Item[] items = new Item[]{
                new Item("Backstage passes", 0, 0)};
        int totalDays = 60;
        updateAndVerifyBackstage(items, totalDays);
    }

    private void updateAndVerifyStandard(Item[] items, int totalDays) {
        GildedRose app = new GildedRose(items);
        int initialQuality = items[0].quality;
        int initialSellIn = items[0].sellIn;
        for (int currentDaysPassed = 1; currentDaysPassed <= totalDays; currentDaysPassed++) {
            app.updateQuality();
            Item updatedItem = app.items[0];
            int remainingSellIn = initialSellIn - currentDaysPassed;
            verifySellInUpdated(remainingSellIn, updatedItem.sellIn);
            verifyQualityUpdatedStandard(remainingSellIn, initialQuality - currentDaysPassed, updatedItem.quality);
        }
    }

    private void updateAndVerifyBrie(Item[] items, int totalDays) {
        GildedRose app = new GildedRose(items);
        int initialQuality = items[0].quality;
        int initialSellIn = items[0].sellIn;
        for (int currentDaysPassed = 1; currentDaysPassed <= totalDays; currentDaysPassed++) {
            app.updateQuality();
            Item updatedItem = app.items[0];
            int remainingSellIn = initialSellIn - currentDaysPassed;
            verifySellInUpdated(remainingSellIn, updatedItem.sellIn);
            verifyQualityUpdatedBrie(remainingSellIn, currentDaysPassed, initialQuality, updatedItem.quality);

        }
    }

    private void updateAndVerifyBackstage(Item[] items, int totalDays) {
        GildedRose app = new GildedRose(items);
        int initialQuality = items[0].quality;
        int initialSellIn = items[0].sellIn;
        for (int currentDaysPassed = 1; currentDaysPassed <= totalDays; currentDaysPassed++) {
            app.updateQuality();
            Item updatedItem = app.items[0];
            int remainingSellIn = initialSellIn - currentDaysPassed;
            verifySellInUpdated(remainingSellIn, updatedItem.sellIn);
            verifyQualityUpdatedBackStage(remainingSellIn, currentDaysPassed, initialQuality, updatedItem.quality);

        }
    }

    private void updateAndVerifySulfuras(Item[] items, int totalDays) {
        GildedRose app = new GildedRose(items);
        for (int i = 1; i <= totalDays; i++) {
            app.updateQuality();
            Item updatedItem = app.items[0];
            assertEquals(items[0].quality,updatedItem.quality);
            assertEquals(items[0].sellIn,updatedItem.sellIn);
        }
    }



    private void verifySellInUpdated(int expected, int updatedSellIn) {
        assertEquals(expected, updatedSellIn);
    }

    private void verifyQualityUpdatedStandard(int remainingSellIn, int virtualQuality, int updatedQuality) {
        boolean sellByDateNotPassed = remainingSellIn >= 0;
        int actualQuality = 0;
        int intermediate = virtualQuality >= 0 ? virtualQuality : 0;
        if (sellByDateNotPassed) {
            actualQuality = intermediate;
        } else {
            int v2 = intermediate + 2 * remainingSellIn;
            actualQuality = v2 > 0 ? v2 : 0;
        }
        assertEquals(actualQuality, updatedQuality);
    }

    private void verifyQualityUpdatedBrie(int remainingSellIn, int currentDaysPassed, int initialQuality, int updatedQuality) {
        boolean sellByDateNotPassed = remainingSellIn >= 0;
        int actualQuality = 0;
        if (sellByDateNotPassed) {
            actualQuality = initialQuality + currentDaysPassed <= 50 ? initialQuality + currentDaysPassed : 50;
        } else {
            int v1 = initialQuality + (currentDaysPassed + remainingSellIn) - 2 * (remainingSellIn);
            actualQuality = v1 <= 50 ? v1 : 50;
        }
        assertEquals(actualQuality, updatedQuality);
    }

    private void verifyQualityUpdatedBackStage(int remainingSellIn, int currentDaysPassed, int initialQuality, int updatedQuality) {
        boolean sellByDateNotPassed = remainingSellIn >= 0;
        int actualQuality = 0;
        if (sellByDateNotPassed) {
            if(remainingSellIn <= 10 && remainingSellIn > 5){
             //   actualQuality = initialQuality +
            }

            actualQuality = initialQuality + currentDaysPassed <= 50 ? initialQuality + currentDaysPassed : 50;
        } else {
            actualQuality = 0;
        }
        assertEquals(actualQuality, updatedQuality);
    }
}
