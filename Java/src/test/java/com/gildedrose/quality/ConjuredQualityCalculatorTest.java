package com.gildedrose.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ConjuredQualityCalculatorTest {

    private ConjuredQualityCalculator conjuredUpdateQualityCalculator;
    private Predicate<Item> isConjuredPredicate;

    @BeforeEach
    void setUp() {
        isConjuredPredicate = mock(Predicate.class);
        conjuredUpdateQualityCalculator = new ConjuredQualityCalculator(isConjuredPredicate);
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        when(isConjuredPredicate.test(item)).thenReturn(true);
        assertThat(conjuredUpdateQualityCalculator.appliesTo(item)).isTrue();

        when(isConjuredPredicate.test(item)).thenReturn(false);
        assertThat(conjuredUpdateQualityCalculator.appliesTo(item)).isFalse();
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
