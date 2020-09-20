package com.gildedrose.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class AgedBrieQualityCalculator extends AbstractQualityCalculator {

    public AgedBrieQualityCalculator(final Predicate<Item> isAgedBriePredicate) {
        super(isAgedBriePredicate);
    }

    @Override
    protected int calculateQuality(final Item item) {
        return item.quality + 1;
    }
}
