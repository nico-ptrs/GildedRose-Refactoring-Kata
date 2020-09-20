package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.BackstagePassesQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BackstagePassesQualityCalculatorTest {

    private BackstagePassesQualityCalculator backstagePassesUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        backstagePassesUpdateQualityCalculator = new BackstagePassesQualityCalculator();
    }

    @Test
    void updateQualityAddsOne() {
        final Item item = new Item("foo", 20, 20);

        final int result = backstagePassesUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(21);
    }

    @Test
    void updateQualityAddsTwoWhenSellInLT10() {
        final Item item1 = new Item("foo", 9, 20);
        final Item item2 = new Item("foo", 10, 20);

        final int result1 = backstagePassesUpdateQualityCalculator.calculate(item1);
        final int result2 = backstagePassesUpdateQualityCalculator.calculate(item2);

        assertThat(result1).isEqualTo(22);
        assertThat(result2).isEqualTo(21);
    }

    @Test
    void updateQualityAddsThreeWhenSellInLT5() {
        final Item item1 = new Item("foo", 4, 20);
        final Item item2 = new Item("foo", 5, 20);

        final int result1 = backstagePassesUpdateQualityCalculator.calculate(item1);
        final int result2 = backstagePassesUpdateQualityCalculator.calculate(item2);

        assertThat(result1).isEqualTo(23);
        assertThat(result2).isEqualTo(22);
    }

    @Test
    void updateQualityIsZeroWhenSellInLT0() {
        final Item item1 = new Item("foo", -1, 20);
        final Item item2 = new Item("foo", 0, 20);

        final int result1 = backstagePassesUpdateQualityCalculator.calculate(item1);
        final int result2 = backstagePassesUpdateQualityCalculator.calculate(item2);

        assertThat(result1).isEqualTo(0);
        assertThat(result2).isEqualTo(23);
    }

    @Test
    void updateQualityCannotBeHigherThanFifty() {
        final Item item1 = new Item("Foo", 20, 50);
        final Item item2 = new Item("Foo", 8, 50);
        final Item item3 = new Item("Foo", 3, 50);

        final int result1 = backstagePassesUpdateQualityCalculator.calculate(item1);
        final int result2 = backstagePassesUpdateQualityCalculator.calculate(item2);
        final int result3 = backstagePassesUpdateQualityCalculator.calculate(item3);

        assertThat(result1).isEqualTo(50);
        assertThat(result2).isEqualTo(50);
        assertThat(result3).isEqualTo(50);
    }
}
