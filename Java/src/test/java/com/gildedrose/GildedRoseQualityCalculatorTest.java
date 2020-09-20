package com.gildedrose;

import com.gildedrose.quality.AgedBrieQualityCalculator;
import com.gildedrose.quality.BackstagePassesQualityCalculator;
import com.gildedrose.quality.ConjuredQualityCalculator;
import com.gildedrose.quality.DefaultQualityCalculator;
import com.gildedrose.quality.QualityCalculator;
import com.gildedrose.quality.SulfurasQualityCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GildedRoseQualityCalculatorTest {

    private GildedRoseQualityCalculator qualityUpdater;

    private DefaultQualityCalculator defaultUpdateQualityCalculator;
    private AgedBrieQualityCalculator agedBrieQualityCalculator;
    private BackstagePassesQualityCalculator backstagePassesQualityCalculator;
    private ConjuredQualityCalculator conjuredQualityCalculator;
    private SulfurasQualityCalculator sulfurasQualityCalculator;

    @BeforeEach
    void setUp() {

        defaultUpdateQualityCalculator = mock(DefaultQualityCalculator.class);
        agedBrieQualityCalculator = mock(AgedBrieQualityCalculator.class);
        backstagePassesQualityCalculator = mock(BackstagePassesQualityCalculator.class);
        conjuredQualityCalculator = mock(ConjuredQualityCalculator.class);
        sulfurasQualityCalculator = mock(SulfurasQualityCalculator.class);
        makeAppliesToReturnFalse(agedBrieQualityCalculator, backstagePassesQualityCalculator, conjuredQualityCalculator, sulfurasQualityCalculator);

        qualityUpdater = new GildedRoseQualityCalculator(defaultUpdateQualityCalculator,
                                                         agedBrieQualityCalculator,
                                                         backstagePassesQualityCalculator,
                                                         conjuredQualityCalculator,
                                                         sulfurasQualityCalculator);
    }

    @Test
    void updateQualityDefaultItem() {
        final Item item = mock(Item.class);
        int expected = 43;

        when(defaultUpdateQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualityAgedBrie() {
        final Item item = mock(Item.class);
        int expected = 13;

        when(agedBrieQualityCalculator.appliesTo(item)).thenReturn(true);
        when(agedBrieQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualityBackstagePasses() {
        final Item item = mock(Item.class);
        int expected = 11;

        when(backstagePassesQualityCalculator.appliesTo(item)).thenReturn(true);
        when(backstagePassesQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualityConjured() {
        final Item item = mock(Item.class);
        int expected = 21;

        when(conjuredQualityCalculator.appliesTo(item)).thenReturn(true);
        when(conjuredQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualitySulfuras() {
        final Item item = mock(Item.class);
        int expected = 33;

        when(sulfurasQualityCalculator.appliesTo(item)).thenReturn(true);
        when(sulfurasQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    private void makeAppliesToReturnFalse(final QualityCalculator... calculators) {
        Arrays.stream(calculators)
              .forEach(calculator -> when(calculator.appliesTo(any(Item.class))).thenReturn(false));
    }
}