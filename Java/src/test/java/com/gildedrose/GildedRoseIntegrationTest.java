package com.gildedrose;

import com.gildedrose.quality.GildedRoseQualityCalculatorFactory;
import com.gildedrose.sellin.GildedRoseSellInCalculatorFactory;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class GildedRoseIntegrationTest {

    private static final String AGED_BRIE = "Aged Brie";
    private static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    private static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    private static final String CONJURED = "Conjured";

    @Test
    void qualityLowersByOne() {
        final Item foo = new Item("foo", 1, 10);
        final Item bar = new Item("bar", 20, 20);
        final GildedRose app = createGildedRose(foo, bar);

        app.updateQuality();

        assertThat(foo.sellIn).isEqualTo(0);
        assertThat(foo.quality).isEqualTo(9);
        assertThat(bar.sellIn).isEqualTo(19);
        assertThat(bar.quality).isEqualTo(19);
    }

    @Test
    void qualityLowersByTwoWhenSellInBecomesLTZero() {
        final Item foo = new Item("foo", 0, 10);
        final Item bar = new Item("bar", -20, 20);
        final GildedRose app = createGildedRose(foo, bar);

        app.updateQuality();

        assertThat(foo.sellIn).isEqualTo(-1);
        assertThat(foo.quality).isEqualTo(8);
        assertThat(bar.sellIn).isEqualTo(-21);
        assertThat(bar.quality).isEqualTo(18);
    }

    @Test
    void qualityNoLowerThanZero() {
        final Item foo = new Item("foo", 10, 0);
        final Item bar = new Item("bar", 10, 1);
        final GildedRose app = createGildedRose(foo, bar);

        app.updateQuality();

        assertThat(foo.sellIn).isEqualTo(9);
        assertThat(foo.quality).isEqualTo(0);
        assertThat(bar.sellIn).isEqualTo(9);
        assertThat(bar.quality).isEqualTo(0);
    }

    @Test
    void qualityRisesWhenAgedBrie() {
        final Item agedBrie = new Item(AGED_BRIE, 10, 10);
        final GildedRose app = createGildedRose(agedBrie);

        app.updateQuality();

        assertThat(agedBrie.sellIn).isEqualTo(9);
        assertThat(agedBrie.quality).isEqualTo(11);
    }

    @Test
    void qualityAndSellInRemainsWhenSulfuras() {
        final Item sulfuras = new Item(SULFURAS, 10, 80);
        final GildedRose app = createGildedRose(sulfuras);

        app.updateQuality();

        assertThat(sulfuras.sellIn).isEqualTo(10);
        assertThat(sulfuras.quality).isEqualTo(80);
    }

    @Test
    void qualityRisesWhenBackstagePassesAndSellInGT10() {
        final Item backstagePass = new Item(BACKSTAGE_PASSES, 11, 20);
        final GildedRose app = createGildedRose(backstagePass);

        app.updateQuality();

        assertThat(backstagePass.sellIn).isEqualTo(10);
        assertThat(backstagePass.quality).isEqualTo(21);
    }

    @Test
    void qualityRisesByTwoWhenBackstagePassesAndSellInLT10() {
        final Item backstagePass1 = new Item(BACKSTAGE_PASSES, 10, 20);
        final Item backstagePass2 = new Item(BACKSTAGE_PASSES, 9, 10);
        final Item backstagePass3 = new Item(BACKSTAGE_PASSES, 6, 15);
        final GildedRose app = createGildedRose(backstagePass1, backstagePass2, backstagePass3);

        app.updateQuality();

        assertThat(backstagePass1.sellIn).isEqualTo(9);
        assertThat(backstagePass1.quality).isEqualTo(22);
        assertThat(backstagePass2.sellIn).isEqualTo(8);
        assertThat(backstagePass2.quality).isEqualTo(12);
        assertThat(backstagePass3.sellIn).isEqualTo(5);
        assertThat(backstagePass3.quality).isEqualTo(17);
    }

    @Test
    void qualityRisesByThreeWhenBackstagePassesAndSellInLT5() {
        final Item backstagePass1 = new Item(BACKSTAGE_PASSES, 5, 20);
        final Item backstagePass2 = new Item(BACKSTAGE_PASSES, 4, 10);
        final Item backstagePass3 = new Item(BACKSTAGE_PASSES, 1, 15);
        final GildedRose app = createGildedRose(backstagePass1, backstagePass2, backstagePass3);

        app.updateQuality();

        assertThat(backstagePass1.sellIn).isEqualTo(4);
        assertThat(backstagePass1.quality).isEqualTo(23);
        assertThat(backstagePass2.sellIn).isEqualTo(3);
        assertThat(backstagePass2.quality).isEqualTo(13);
        assertThat(backstagePass3.sellIn).isEqualTo(0);
        assertThat(backstagePass3.quality).isEqualTo(18);
    }

    @Test
    void qualityEqZeroWhenBackstagePassesAndSellInLT0() {
        final Item backstagePass = new Item(BACKSTAGE_PASSES, 0, 20);
        final GildedRose app = createGildedRose(backstagePass);

        app.updateQuality();

        assertThat(backstagePass.sellIn).isEqualTo(-1);
        assertThat(backstagePass.quality).isEqualTo(0);
    }

    @Test
    void qualityNoHigherThanFifty() {
        final Item agedBrie1 = new Item(AGED_BRIE, 10, 49);
        final Item agedBrie2 = new Item(AGED_BRIE, 10, 50);
        final Item backstagePass1 = new Item(BACKSTAGE_PASSES, 12, 49);
        final Item backstagePass2 = new Item(BACKSTAGE_PASSES, 12, 50);
        final GildedRose app = createGildedRose(agedBrie2, agedBrie1, backstagePass1, backstagePass2);

        app.updateQuality();

        assertThat(agedBrie1.sellIn).isEqualTo(9);
        assertThat(agedBrie1.quality).isEqualTo(50);
        assertThat(agedBrie2.sellIn).isEqualTo(9);
        assertThat(agedBrie2.quality).isEqualTo(50);
        assertThat(backstagePass1.sellIn).isEqualTo(11);
        assertThat(backstagePass1.quality).isEqualTo(50);
        assertThat(backstagePass2.sellIn).isEqualTo(11);
        assertThat(backstagePass2.quality).isEqualTo(50);
    }

    @Test
    void qualityLowersByTwoWhenConjured() {
        final Item backstagePass = new Item(CONJURED, 12, 20);
        final GildedRose app = createGildedRose(backstagePass);

        app.updateQuality();

        assertThat(backstagePass.sellIn).isEqualTo(11);
        assertThat(backstagePass.quality).isEqualTo(18);
    }

    private GildedRose createGildedRose(Item... items) {
        return new GildedRose(new GildedRoseSellInCalculatorFactory(), new GildedRoseQualityCalculatorFactory(), items);
    }
}
