package com.gildedrose.strategy;

import com.gildedrose.GildedRose;
import com.gildedrose.Item;
import com.gildedrose.constants.ItemType;
import com.gildedrose.constants.SellInStage;

public class QualityUpdateStrategyFactory {

    public static QualityUpdateStrategy getQualityUpdateStrategy(ItemType itemType) {
        switch (itemType) {
            case SULFURAS:
                return getLegendaryStrategy();
            case AGED_BRIE:
                return getUnperishableUpperBoundedStrategy();
            case BACKSTAGE:
                return getUnperishableStagedBoundedStrategy();
            case CONJURED:
                return getAugmentedPerishableStrategy();
            default:
                return getStandardStrategy();

        }
    }

    private static QualityUpdateStrategy getUnperishableStagedBoundedStrategy() {
        return new QualityUpdateStrategy() {
            @Override
            public void updateQuality(Item item) {
                //    Item updatedItem = new Item(item.name, item.sellIn - 1, item.quality);
                item.sellIn = item.sellIn - 1;
                //TODO replace with switch
                if (item.sellIn <= SellInStage.REMOTE.getDaysLeft()
                        && item.sellIn > SellInStage.APPROACHING.getDaysLeft()) {

                    int bumpedQuality = item.quality + 2;
                    item.quality = bumpedQuality <= GildedRose.MAX_QUALITY
                            ? bumpedQuality : GildedRose.MAX_QUALITY;

                } else if (item.sellIn <= SellInStage.APPROACHING.getDaysLeft()
                        && item.sellIn > SellInStage.PASSED.getDaysLeft()) {

                    int bumpedQuality = item.quality + 3;
                    item.quality = bumpedQuality <= GildedRose.MAX_QUALITY
                            ? bumpedQuality : GildedRose.MAX_QUALITY;
                } else {
                    item.quality = 0;
                }
            }
        };
    }

    private static QualityUpdateStrategy getUnperishableUpperBoundedStrategy() {
        return new QualityUpdateStrategy() {
            @Override
            public void updateQuality(Item item) {
                item.sellIn = item.sellIn - 1;
                int bumpedQuality = item.quality + 1;
                item.quality = bumpedQuality <= GildedRose.MAX_QUALITY
                        ? bumpedQuality : GildedRose.MAX_QUALITY;
            }
        };
    }

    private static QualityUpdateStrategy getLegendaryStrategy() {
        return new QualityUpdateStrategy() {
            @Override
            public void updateQuality(Item item) {
                //do nothing
            }
        };
    }

    private static QualityUpdateStrategy getStandardStrategy() {
        return new QualityUpdateStrategy() {
            @Override
            public void updateQuality(Item item) {
                item.sellIn = item.sellIn - 1;
                int diminishedQuality = item.quality - 1;
                item.quality = diminishedQuality <= 0 ? 0 : diminishedQuality;
            }
        };
    }

    private static QualityUpdateStrategy getAugmentedPerishableStrategy() {
        return new QualityUpdateStrategy() {
            @Override
            public void updateQuality(Item item) {
                item.sellIn = item.sellIn - 1;
                int diminishedQuality = item.quality - 2;
                item.quality = diminishedQuality <= 0 ? 0 : diminishedQuality;
            }
        };
    }
}
