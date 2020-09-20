package com.gildedrose.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AgedBrieQualityCalculatorTest {

    private AgedBrieQualityCalculator agedBrieUpdateQualityCalculator;
    private Predicate<Item> isAgedBriePredicate;

    @BeforeEach
    void setUp() {
        isAgedBriePredicate = mock(Predicate.class);
        agedBrieUpdateQualityCalculator = new AgedBrieQualityCalculator(isAgedBriePredicate);
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        when(isAgedBriePredicate.test(item)).thenReturn(true);
        assertThat(agedBrieUpdateQualityCalculator.appliesTo(item)).isTrue();

        when(isAgedBriePredicate.test(item)).thenReturn(false);
        assertThat(agedBrieUpdateQualityCalculator.appliesTo(item)).isFalse();
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
