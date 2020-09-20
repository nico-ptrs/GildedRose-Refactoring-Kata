package com.gildedrose.quality;

import com.gildedrose.Item;
import com.gildedrose.quality.SulfurasQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SulfurasQualityCalculatorTest {

    private SulfurasQualityCalculator sulfurasUpdateQualityCalculator;

    @BeforeEach
    void setUp() {
        sulfurasUpdateQualityCalculator = new SulfurasQualityCalculator();
    }

    @Test
    void updateQualityRemainsEighty() {
        final Item item = new Item("Foo", 17, 80);

        final int result = sulfurasUpdateQualityCalculator.calculate(item);

        assertThat(result).isEqualTo(80);
    }
}