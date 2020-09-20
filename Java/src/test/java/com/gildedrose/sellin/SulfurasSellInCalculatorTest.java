package com.gildedrose.sellin;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SulfurasSellInCalculatorTest {

    private SulfurasSellInCalculator sulfurasUpdateSellInCalculator;

    @BeforeEach
    void setUp() {
        sulfurasUpdateSellInCalculator = new SulfurasSellInCalculator();
    }

    @Test
    void calculateSellIn() {
        final Item item = new Item("Foo", 34, 10);

        final int result = sulfurasUpdateSellInCalculator.calculate(item);

        assertThat(result).isEqualTo(item.sellIn);
    }
}