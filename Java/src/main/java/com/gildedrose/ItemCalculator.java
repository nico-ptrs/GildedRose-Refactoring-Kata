package com.gildedrose;

public interface ItemCalculator {
    boolean appliesTo(Item item);

    int calculate(Item item);
}
