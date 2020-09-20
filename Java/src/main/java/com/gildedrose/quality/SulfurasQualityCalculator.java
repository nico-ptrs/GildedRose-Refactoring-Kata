package com.gildedrose.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class SulfurasQualityCalculator implements QualityCalculator {

    private final Predicate<Item> isSulfurasPredicate;

    public SulfurasQualityCalculator(final Predicate<Item> isSulfurasPredicate) {
        this.isSulfurasPredicate = isSulfurasPredicate;
    }

    @Override
    public boolean appliesTo(final Item item) {
        return this.isSulfurasPredicate.test(item);
    }

    @Override
    public int calculate(final Item item) {
        return 80;
    }
}
