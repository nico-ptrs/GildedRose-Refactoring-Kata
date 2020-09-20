package com.gildedrose.sellin;

import com.gildedrose.Item;

public class DefaultSellInCalculator implements SellInCalculator {

    @Override
    public boolean appliesTo(final Item item) {
        return true;
    }

    @Override
    public int calculate(final Item item) {
        return item.sellIn - 1;
    }
}
