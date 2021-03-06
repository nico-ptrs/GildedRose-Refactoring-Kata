package com.gildedrose;

import com.gildedrose.sellin.DefaultSellInCalculator;
import com.gildedrose.sellin.SulfurasSellInCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GildedRoseSellInCalculatorTest {

    private GildedRoseSellInCalculator sellInUpdater;

    private DefaultSellInCalculator defaultUpdateSellInCalculator;
    private SulfurasSellInCalculator sulfurasUpdateSellInCalculator;

    @BeforeEach
    void setUp() {
        defaultUpdateSellInCalculator = mock(DefaultSellInCalculator.class);
        sulfurasUpdateSellInCalculator = mock(SulfurasSellInCalculator.class);

        sellInUpdater = new GildedRoseSellInCalculator(defaultUpdateSellInCalculator, sulfurasUpdateSellInCalculator);
    }

    @Test
    void calculateSellInOfDefaultItem() {
        final Item item = mock(Item.class);
        final int expected = 23;

        when(sulfurasUpdateSellInCalculator.appliesTo(item)).thenReturn(false);
        when(defaultUpdateSellInCalculator.calculate(item)).thenReturn(expected);

        final int result = sellInUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void calculateSellInOfSulfurasItem() {
        final Item item = mock(Item.class);
        final int expected = 26;

        when(sulfurasUpdateSellInCalculator.appliesTo(item)).thenReturn(true);
        when(sulfurasUpdateSellInCalculator.calculate(item)).thenReturn(expected);

        final int result = sellInUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }
}