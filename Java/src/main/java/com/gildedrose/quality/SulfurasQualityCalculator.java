package com.gildedrose.quality;

import com.gildedrose.Item;

public class SulfurasQualityCalculator implements QualityCalculator {

    @Override
    public int calculate(final Item item) {
        return 80;
    }
}
