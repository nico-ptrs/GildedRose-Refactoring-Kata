package com.gildedrose;

import com.gildedrose.quality.GildedRoseQualityCalculatorFactory;
import com.gildedrose.sellin.GildedRoseSellInCalculatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class GildedRoseTest {

    private GildedRoseSellInCalculatorFactory sellInCalculatorFactory;
    private GildedRoseQualityCalculatorFactory qualityCalculatorFactory;
    private GildedRoseSellInCalculator sellInCalculator;
    private GildedRoseQualityCalculator qualityCalculator;

    @BeforeEach
    void setUp() {
        sellInCalculatorFactory = mock(GildedRoseSellInCalculatorFactory.class);
        qualityCalculatorFactory = mock(GildedRoseQualityCalculatorFactory.class);
        sellInCalculator = mock(GildedRoseSellInCalculator.class);
        qualityCalculator = mock(GildedRoseQualityCalculator.class);

        when(sellInCalculatorFactory.create()).thenReturn(sellInCalculator);
        when(qualityCalculatorFactory.create()).thenReturn(qualityCalculator);
    }

    @Test
    void updateQuality() {
        final Item item1 = mock(Item.class);
        final Item item2 = mock(Item.class);
        final Item item3 = mock(Item.class);

        final GildedRose gildedRose = new GildedRose(sellInCalculatorFactory, qualityCalculatorFactory, item1, item2, item3);

        gildedRose.updateQuality();

        final InOrder inOrder = inOrder(sellInCalculator, qualityCalculator);
        inOrder.verify(sellInCalculator).calculate(item1);
        inOrder.verify(qualityCalculator).calculate(item1);
        inOrder.verify(sellInCalculator).calculate(item2);
        inOrder.verify(qualityCalculator).calculate(item2);
        inOrder.verify(sellInCalculator).calculate(item3);
        inOrder.verify(qualityCalculator).calculate(item3);
    }
}