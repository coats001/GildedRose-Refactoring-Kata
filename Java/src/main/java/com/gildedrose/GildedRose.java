package com.gildedrose;

import com.gildedrose.constants.ItemType;
import com.gildedrose.strategy.QualityUpdateStrategyFactory;

public class GildedRose {
    public static final int MAX_QUALITY = 50;
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (int i = 0; i < items.length; i++) {
            QualityUpdateStrategyFactory
                    .getQualityUpdateStrategy(ItemType.fromString(items[i].name))
                    .updateQuality(items[i]);
        }
    }
}