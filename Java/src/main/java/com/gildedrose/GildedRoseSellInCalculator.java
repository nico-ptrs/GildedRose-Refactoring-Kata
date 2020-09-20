package com.gildedrose;

import com.gildedrose.sellin.SellInCalculator;

import java.util.Arrays;

public class GildedRoseSellInCalculator {

    private final SellInCalculator defaultUpdateSellInCalculator;
    private final SellInCalculator[] sellInCalculators;

    public GildedRoseSellInCalculator(final SellInCalculator defaultUpdateSellInCalculator,
                                      final SellInCalculator... sellInCalculators) {
        this.defaultUpdateSellInCalculator = defaultUpdateSellInCalculator;
        this.sellInCalculators = sellInCalculators;
    }

    public int calculate(final Item item) {
        return Arrays.stream(sellInCalculators)
                     .filter(calculator -> calculator.appliesTo(item))
                     .findFirst()
                     .orElse(defaultUpdateSellInCalculator)
                     .calculate(item);
    }
}
