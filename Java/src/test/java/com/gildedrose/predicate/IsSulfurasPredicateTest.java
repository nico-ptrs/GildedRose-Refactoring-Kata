package com.gildedrose.predicate;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IsSulfurasPredicateTest {

    private IsSulfurasPredicate isSulfurasPredicate;

    @BeforeEach
    void setUp() {
        isSulfurasPredicate = new IsSulfurasPredicate();
    }

    @Test
    void testIsSulfuras() {
        assertThat(isSulfurasPredicate.test(new Item("Sulfuras, Hand of Ragnaros", 1, 1))).isTrue();
    }

    @Test
    void testIsNotSulfuras() {
        assertThat(isSulfurasPredicate.test(new Item("Foo", 1, 1))).isFalse();
    }
}