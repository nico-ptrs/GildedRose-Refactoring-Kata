package com.gildedrose.quality;

import com.gildedrose.Item;

public abstract class AbstractQualityCalculator implements QualityCalculator {

    protected abstract int calculateQuality(Item item);

    @Override
    public int calculate(final Item item) {
        if (item.quality > 0 && item.quality < 50) {
            return calculateQuality(item);
        } else {
            return item.quality;
        }
    }
}
