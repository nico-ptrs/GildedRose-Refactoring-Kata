package com.gildedrose.quality;

import com.gildedrose.GildedRoseQualityCalculator;
import com.gildedrose.predicate.IsAgedBriePredicate;
import com.gildedrose.predicate.IsBackstagePassesPredicate;
import com.gildedrose.predicate.IsConjuredPredicate;
import com.gildedrose.predicate.IsSulfurasPredicate;

public class GildedRoseQualityCalculatorFactory {

    public GildedRoseQualityCalculator create() {
        return new GildedRoseQualityCalculator(new DefaultQualityCalculator(),
                                               new IsAgedBriePredicate(),
                                               new AgedBrieQualityCalculator(),
                                               new IsBackstagePassesPredicate(),
                                               new BackstagePassesQualityCalculator(),
                                               new IsConjuredPredicate(),
                                               new ConjuredQualityCalculator(),
                                               new IsSulfurasPredicate(),
                                               new SulfurasQualityCalculator());
    }
}
