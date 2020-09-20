package com.gildedrose.quality;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SulfurasQualityCalculatorTest {

    private SulfurasQualityCalculator sulfurasUpdateQualityCalculator;
    private Predicate<Item> isSulfurasPredicate;

    @BeforeEach
    void setUp() {
        isSulfurasPredicate = mock(Predicate.class);
        sulfurasUpdateQualityCalculator = new SulfurasQualityCalculator(isSulfurasPredicate);
    }

    @Test
    void appliesTo() {
        final Item item = mock(Item.class);
        when(isSulfurasPredicate.test(item)).thenReturn(true);
        assertThat(sulfurasUpdateQualityCalculator.appliesTo(item)).isTrue();

        when(isSulfurasPredicate.test(item)).thenReturn(false);
        assertThat(sulfurasUpdateQualityCalculator.appliesTo(item)).isFalse();
    }

    @Test
    void updateQualityRemainsEighty() {
        final Item item = new Item("Foo", 17, 80);

        final int result = sulfurasUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(80);
    }
}