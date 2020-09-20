package com.gildedrose;

import com.gildedrose.quality.AgedBrieQualityCalculator;
import com.gildedrose.quality.BackstagePassesQualityCalculator;
import com.gildedrose.quality.ConjuredQualityCalculator;
import com.gildedrose.quality.DefaultQualityCalculator;
import com.gildedrose.quality.SulfurasQualityCalculator;
import com.gildedrose.predicate.IsAgedBriePredicate;
import com.gildedrose.predicate.IsBackstagePassesPredicate;
import com.gildedrose.predicate.IsConjuredPredicate;
import com.gildedrose.predicate.IsSulfurasPredicate;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GildedRoseQualityCalculatorTest {

    private GildedRoseQualityCalculator qualityUpdater;

    private DefaultQualityCalculator defaultUpdateQualityCalculator;
    private AgedBrieQualityCalculator agedBrieQualityCalculator;
    private IsAgedBriePredicate isAgedBriePredicate;
    private IsBackstagePassesPredicate isBackstagePassesPredicate;
    private BackstagePassesQualityCalculator backstagePassesQualityCalculator;
    private IsConjuredPredicate isConjuredPredicate;
    private ConjuredQualityCalculator conjuredQualityCalculator;
    private IsSulfurasPredicate isSulfurasPredicate;
    private SulfurasQualityCalculator sulfurasQualityCalculator;

    @BeforeEach
    void setUp() {
        isAgedBriePredicate = mock(IsAgedBriePredicate.class);
        isBackstagePassesPredicate = mock(IsBackstagePassesPredicate.class);
        isConjuredPredicate = mock(IsConjuredPredicate.class);
        isSulfurasPredicate = mock(IsSulfurasPredicate.class);
        makePredicatesReturnFalse(isAgedBriePredicate, isBackstagePassesPredicate, isConjuredPredicate, isSulfurasPredicate);

        defaultUpdateQualityCalculator = mock(DefaultQualityCalculator.class);
        agedBrieQualityCalculator = mock(AgedBrieQualityCalculator.class);
        backstagePassesQualityCalculator = mock(BackstagePassesQualityCalculator.class);
        conjuredQualityCalculator = mock(ConjuredQualityCalculator.class);
        sulfurasQualityCalculator = mock(SulfurasQualityCalculator.class);

        qualityUpdater = new GildedRoseQualityCalculator(defaultUpdateQualityCalculator,
                                                         isAgedBriePredicate,
                                                         agedBrieQualityCalculator,
                                                         isBackstagePassesPredicate,
                                                         backstagePassesQualityCalculator,
                                                         isConjuredPredicate,
                                                         conjuredQualityCalculator,
                                                         isSulfurasPredicate,
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

        when(isAgedBriePredicate.test(item)).thenReturn(true);
        when(agedBrieQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualityBackstagePasses() {
        final Item item = mock(Item.class);
        int expected = 11;

        when(isBackstagePassesPredicate.test(item)).thenReturn(true);
        when(backstagePassesQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualityConjured() {
        final Item item = mock(Item.class);
        int expected = 21;

        when(isConjuredPredicate.test(item)).thenReturn(true);
        when(conjuredQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    @Test
    void updateQualitySulfuras() {
        final Item item = mock(Item.class);
        int expected = 33;

        when(isSulfurasPredicate.test(item)).thenReturn(true);
        when(sulfurasQualityCalculator.calculate(item)).thenReturn(expected);

        final int result = qualityUpdater.calculate(item);

        assertThat(result).isEqualTo(expected);
    }

    private void makePredicatesReturnFalse(final Predicate<Item>... predicates) {
        Lists.list(predicates)
             .forEach(predicate -> when(predicate.test(any(Item.class))).thenReturn(false));
    }
}