package com.gildedrose.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public abstract class AbstractQualityCalculator implements QualityCalculator {

    private final Predicate<Item> appliesToPredicate;

    public AbstractQualityCalculator(final Predicate<Item> appliesToPredicate) {
        this.appliesToPredicate = appliesToPredicate;
    }

    protected abstract int calculateQuality(Item item);

    @Override
    public boolean appliesTo(final Item item) {
        return this.appliesToPredicate.test(item);
    }

    @Override
    public int calculate(final Item item) {
        if (item.quality > 0 && item.quality < 50) {
            return calculateQuality(item);
        } else {
            return item.quality;
        }
    }
}
