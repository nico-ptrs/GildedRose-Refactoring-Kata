package com.gildedrose.predicate;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class IsBackstagePassesPredicate implements Predicate<Item> {

    @Override
    public boolean test(final Item item) {
        return item.name.equals("Backstage passes to a TAFKAL80ETC concert");
    }
}
