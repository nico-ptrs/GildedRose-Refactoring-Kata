package com.gildedrose;

import com.gildedrose.quality.QualityCalculator;

import java.util.Arrays;

public class GildedRoseQualityCalculator {

    private final QualityCalculator defaultUpdateQualityCalculator;
    private final QualityCalculator[] qualityCalculator;

    public GildedRoseQualityCalculator(final QualityCalculator defaultUpdateQualityCalculator,
                                       final QualityCalculator... qualityCalculator) {
        this.defaultUpdateQualityCalculator = defaultUpdateQualityCalculator;
        this.qualityCalculator = qualityCalculator;
    }

    public int calculate(final Item item) {
        return Arrays.stream(qualityCalculator)
                     .filter(calculator -> calculator.appliesTo(item))
                     .findFirst()
                     .orElse(defaultUpdateQualityCalculator)
                     .calculate(item);
    }
}
