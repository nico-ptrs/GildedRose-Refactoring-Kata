package com.gildedrose.quality;

import com.gildedrose.Item;

public class DefaultQualityCalculator extends AbstractQualityCalculator {

    @Override
    protected int calculateQuality(final Item item) {
        if (isExpired(item)) {
            return item.quality - 2;
        }
        return item.quality - 1;
    }

    private boolean isExpired(Item item) {
        return item.sellIn < 0;
    }
}
