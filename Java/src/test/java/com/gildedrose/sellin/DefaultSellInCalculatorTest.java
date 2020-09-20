package com.gildedrose.sellin;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

class DefaultSellInCalculatorTest {

    private DefaultSellInCalculator defaultUpdateSellInCalculator;

    @BeforeEach
    void setUp() {
        defaultUpdateSellInCalculator = new DefaultSellInCalculator();
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        assertThat(defaultUpdateSellInCalculator.appliesTo(item)).isTrue();
    }

    @Test
    void calculateSellIn() {
        final Item item = new Item("Foo", 10, 10);

        final int result = defaultUpdateSellInCalculator.calculate(item);

        assertThat(result).isEqualTo(9);
    }
}