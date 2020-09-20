package com.gildedrose.predicate;

import com.gildedrose.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class IsAgedBriePredicateTest {

    private IsAgedBriePredicate isAgedBriePredicate;

    @BeforeEach
    void setUp() {
        isAgedBriePredicate = new IsAgedBriePredicate();
    }

    @Test
    void testIsAgedBrie() {
        assertThat(isAgedBriePredicate.test(new Item("Aged Brie", 1, 1))).isTrue();
    }

    @Test
    void testIsNotAgedBrie() {
        assertThat(isAgedBriePredicate.test(new Item("Foo", 1, 1))).isFalse();
    }
}