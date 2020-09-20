package com.gildedrose.sellin;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class SulfurasSellInCalculator implements SellInCalculator {

    private final Predicate<Item> isSulfurasPredicate;

    public SulfurasSellInCalculator(final Predicate<Item> isSulfurasPredicate) {
        this.isSulfurasPredicate = isSulfurasPredicate;
    }

    @Override
    public boolean appliesTo(final Item item) {
        return isSulfurasPredicate.test(item);
    }

    @Override
    public int calculate(final Item item) {
        return item.sellIn;
    }
}
