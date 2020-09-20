package com.gildedrose.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DefaultQualityCalculatorTest {

    private DefaultQualityCalculator defaultUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        defaultUpdateQualityCalculator = new DefaultQualityCalculator();
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        assertThat(defaultUpdateQualityCalculator.appliesTo(item)).isTrue();
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