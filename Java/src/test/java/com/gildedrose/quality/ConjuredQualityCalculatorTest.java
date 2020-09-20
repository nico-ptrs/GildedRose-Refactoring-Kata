package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.ConjuredQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ConjuredQualityCalculatorTest {

    private ConjuredQualityCalculator conjuredUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        conjuredUpdateQualityCalculator = new ConjuredQualityCalculator();
    }

    @Test
    void updateQualityLowersByTwo() {
        final Item item = new Item("Foo", 10, 20);

        final int result = conjuredUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(18);
    }

    @Test
    void updateQualityCannotBeLowerThanZero() {
        final Item item = new Item("Foo", 10, 0);

        final int result = conjuredUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(0);
    }
}
