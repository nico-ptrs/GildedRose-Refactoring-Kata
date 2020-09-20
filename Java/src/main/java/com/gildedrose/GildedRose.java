package com.gildedrose;

import com.gildedrose.quality.GildedRoseQualityCalculatorFactory;
import com.gildedrose.sellin.GildedRoseSellInCalculatorFactory;

public class GildedRose {

    private final GildedRoseSellInCalculator sellInCalculator;
    private final GildedRoseQualityCalculator qualityCalculator;
    Item[] items;

    public GildedRose(final GildedRoseSellInCalculatorFactory gildedRoseSellInCalculatorFactory,
                      final GildedRoseQualityCalculatorFactory qualityCalculatorFactory,
                      final Item... items) {
        sellInCalculator = gildedRoseSellInCalculatorFactory.create();
        qualityCalculator = qualityCalculatorFactory.create();
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            item.sellIn = sellInCalculator.calculate(item);
            item.quality = qualityCalculator.calculate(item);
        }
    }
}
