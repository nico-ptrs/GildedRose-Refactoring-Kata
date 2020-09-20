package com.gildedrose.sellin;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SulfurasSellInCalculatorTest {

    private Predicate<Item> isSulfurasPredicate;
    private SulfurasSellInCalculator sulfurasUpdateSellInCalculator;

    @BeforeEach
    void setUp() {
        isSulfurasPredicate = mock(Predicate.class);
        sulfurasUpdateSellInCalculator = new SulfurasSellInCalculator(isSulfurasPredicate);
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        when(isSulfurasPredicate.test(item)).thenReturn(true);
        assertThat(sulfurasUpdateSellInCalculator.appliesTo(item)).isTrue();

        when(isSulfurasPredicate.test(item)).thenReturn(false);
        assertThat(sulfurasUpdateSellInCalculator.appliesTo(item)).isFalse();
    }

    @Test
    void calculateSellIn() {
        final Item item = new Item("Foo", 34, 10);

        final int result = sulfurasUpdateSellInCalculator.calculate(item);

        assertThat(result).isEqualTo(item.sellIn);
    }
}