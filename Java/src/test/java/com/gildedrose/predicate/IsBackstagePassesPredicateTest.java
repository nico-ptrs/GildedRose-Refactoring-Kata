package com.gildedrose.predicate;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IsBackstagePassesPredicateTest {

    private IsBackstagePassesPredicate isBackstagePassesPredicate;

    @BeforeEach
    void setUp() {
        isBackstagePassesPredicate = new IsBackstagePassesPredicate();
    }

    @Test
    void testIsBackstagePasses() {
        assertThat(isBackstagePassesPredicate.test(new Item("Backstage passes to a TAFKAL80ETC concert", 1, 1))).isTrue();
    }

    @Test
    void testIsNotBackstagePasses() {
        assertThat(isBackstagePassesPredicate.test(new Item("Foo", 1, 1))).isFalse();
    }
}