package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.DefaultQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DefaultQualityCalculatorTest {

    private DefaultQualityCalculator defaultUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        defaultUpdateQualityCalculator = new DefaultQualityCalculator();
    }

    @Test
    void calculate() {
        final Item item = new Item("Foo", 20, 10);

        final int result = defaultUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(9);
    }

    @Test
    void calculateLowersByTwoWhenSellInExpired() {
        final Item item = new Item("Foo", -1, 10);

        final int result = defaultUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(8);
    }

    @Test
    void calculateCannotBeLowerThanZero() {
        final Item item = new Item("Foo", 10, 0);

        final int result = defaultUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(0);
    }
}