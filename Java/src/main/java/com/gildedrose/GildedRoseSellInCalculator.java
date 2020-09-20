package com.gildedrose;

import com.gildedrose.predicate.IsSulfurasPredicate;
import com.gildedrose.sellin.DefaultSellInCalculator;
import com.gildedrose.sellin.SulfurasSellInCalculator;

public class GildedRoseSellInCalculator {

    private final DefaultSellInCalculator defaultUpdateSellInCalculator;
    private final IsSulfurasPredicate isSulfurasPredicate;
    private final SulfurasSellInCalculator sulfurasUpdateSellInCalculator;

    public GildedRoseSellInCalculator(final DefaultSellInCalculator defaultUpdateSellInCalculator,
                                      final IsSulfurasPredicate isSulfurasPredicate,
                                      final SulfurasSellInCalculator sulfurasUpdateSellInCalculator) {
        this.isSulfurasPredicate = isSulfurasPredicate;
        this.defaultUpdateSellInCalculator = defaultUpdateSellInCalculator;
        this.sulfurasUpdateSellInCalculator = sulfurasUpdateSellInCalculator;
    }

    public int calculate(final Item item) {
        if (isSulfurasPredicate.test(item)) {
            return sulfurasUpdateSellInCalculator.calculate(item);
        } else {
            return defaultUpdateSellInCalculator.calculate(item);
        }
    }
}
