package com.gildedrose.constants;

public enum ItemType {
    STANDARD("+5 Dexterity Vest"),
    AGED_BRIE("Aged Brie"),
    BACKSTAGE("Backstage passes to a TAFKAL80ETC concert"),
    SULFURAS("Sulfuras, Hand of Ragnaros"),
    CONJURED("Conjured Mana Cake");


    public final String name;

    private ItemType(String name ) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static ItemType fromString(String string) {
        for (ItemType itemType : values()) {
            if (itemType.name.equals(string)) {
                return itemType;
            }
        }
        return STANDARD;
    }

}
