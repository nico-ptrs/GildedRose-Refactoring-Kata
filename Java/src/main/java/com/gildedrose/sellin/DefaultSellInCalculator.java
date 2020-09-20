package com.gildedrose.sellin;

import com.gildedrose.Item;

public class DefaultSellInCalculator implements SellInCalculator {

    @Override
    public int calculate(final Item item) {
        return item.sellIn - 1;
    }
}
