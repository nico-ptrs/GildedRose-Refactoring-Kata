package com.gildedrose.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class ConjuredQualityCalculator extends AbstractQualityCalculator {

    public ConjuredQualityCalculator(final Predicate<Item> isConjuredPredicate) {
        super(isConjuredPredicate);
    }

    @Override
    protected int calculateQuality(final Item item) {
        return item.quality - 2;
    }
}
