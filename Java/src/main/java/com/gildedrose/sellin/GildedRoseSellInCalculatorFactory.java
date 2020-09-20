package com.gildedrose.sellin;

import com.gildedrose.GildedRoseSellInCalculator;
import com.gildedrose.predicate.IsSulfurasPredicate;

public class GildedRoseSellInCalculatorFactory {

    public GildedRoseSellInCalculator create() {
        return new GildedRoseSellInCalculator(new DefaultSellInCalculator(), new SulfurasSellInCalculator(new IsSulfurasPredicate()));
    }
}
