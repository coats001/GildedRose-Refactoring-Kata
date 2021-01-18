package com.gildedrose.constants;

public enum SellInStage {
    REMOTE(10),
    APPROACHING(5),
    PASSED(0);

    private int daysLeft;

    SellInStage(int daysLeft){
        this.daysLeft = daysLeft;
    }

    public int getDaysLeft() {
        return daysLeft;
    }
}
