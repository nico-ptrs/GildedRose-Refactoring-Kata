package com.gildedrose.quality;

import com.gildedrose.Item;

public class ConjuredQualityCalculator extends AbstractQualityCalculator {

    @Override
    protected int calculateQuality(final Item item) {
        return item.quality - 2;
    }
}
