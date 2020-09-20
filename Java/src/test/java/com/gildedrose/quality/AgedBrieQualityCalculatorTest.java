package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.AgedBrieQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AgedBrieQualityCalculatorTest {

    private AgedBrieQualityCalculator agedBrieUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        agedBrieUpdateQualityCalculator = new AgedBrieQualityCalculator();
    }

    @Test
    void updateQualityAddsOne() {
        final Item item = new Item("Foo", 20, 20);

        final int result = agedBrieUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(21);
    }

    @Test
    void updateQualityCannotBeHigherThanFifty() {
        final Item item = new Item("Foo", 20, 50);

        final int result = agedBrieUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(50);
    }
}
