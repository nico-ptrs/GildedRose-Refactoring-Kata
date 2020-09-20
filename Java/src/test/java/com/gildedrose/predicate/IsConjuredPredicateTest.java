package com.gildedrose.predicate;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class IsConjuredPredicateTest {

    private IsConjuredPredicate isConjuredPredicate;

    @BeforeEach
    void setUp() {
        isConjuredPredicate = new IsConjuredPredicate();
    }

    @Test
    void testIsConjured() {
        assertThat(isConjuredPredicate.test(new Item("Conjured", 1, 1))).isTrue();
    }

    @Test
    void testIsNotConjured() {
        assertThat(isConjuredPredicate.test(new Item("Foo", 1, 1))).isFalse();
    }
}