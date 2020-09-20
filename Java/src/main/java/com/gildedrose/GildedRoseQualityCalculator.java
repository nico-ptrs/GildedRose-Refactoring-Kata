package com.gildedrose;

import com.gildedrose.predicate.IsAgedBriePredicate;
import com.gildedrose.predicate.IsBackstagePassesPredicate;
import com.gildedrose.predicate.IsConjuredPredicate;
import com.gildedrose.predicate.IsSulfurasPredicate;
import com.gildedrose.quality.AgedBrieQualityCalculator;
import com.gildedrose.quality.BackstagePassesQualityCalculator;
import com.gildedrose.quality.ConjuredQualityCalculator;
import com.gildedrose.quality.DefaultQualityCalculator;
import com.gildedrose.quality.QualityCalculator;
import com.gildedrose.quality.SulfurasQualityCalculator;

import java.util.HashMap;
import java.util.function.Predicate;

public class GildedRoseQualityCalculator {

    private final HashMap<Predicate<Item>, QualityCalculator> mapping;
    private final DefaultQualityCalculator defaultUpdateQualityCalculator;

    public GildedRoseQualityCalculator(final DefaultQualityCalculator defaultUpdateQualityCalculator,
                                       final IsAgedBriePredicate isAgedBriePredicate,
                                       final AgedBrieQualityCalculator agedBrieQualityCalculator,
                                       final IsBackstagePassesPredicate isBackstagePassesPredicate,
                                       final BackstagePassesQualityCalculator backstagePassesQualityCalculator,
                                       final IsConjuredPredicate isConjuredPredicate,
                                       final ConjuredQualityCalculator conjuredQualityCalculator,
                                       final IsSulfurasPredicate isSulfurasPredicate,
                                       final SulfurasQualityCalculator sulfurasQualityCalculator) {
        this.defaultUpdateQualityCalculator = defaultUpdateQualityCalculator;
        mapping = new HashMap<>();
        mapping.put(isAgedBriePredicate, agedBrieQualityCalculator);
        mapping.put(isBackstagePassesPredicate, backstagePassesQualityCalculator);
        mapping.put(isConjuredPredicate, conjuredQualityCalculator);
        mapping.put(isSulfurasPredicate, sulfurasQualityCalculator);
    }

    public int calculate(final Item item) {
        return mapping.keySet()
                      .stream()
                      .filter(predicate -> predicate.test(item))
                      .findFirst()
                      .map(mapping::get)
                      .orElse(defaultUpdateQualityCalculator)
                      .calculate(item);
    }
}
