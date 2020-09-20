package com.gildedrose.predicate;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class IsAgedBriePredicate implements Predicate<Item> {

    @Override
    public boolean test(final Item item) {
        return item.name.equals("Aged Brie");
    }


}
