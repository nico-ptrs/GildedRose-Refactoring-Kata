package com.gildedrose.quality;

import com.gildedrose.Item;

import java.util.function.Predicate;

public class BackstagePassesQualityCalculator extends AbstractQualityCalculator {

    public BackstagePassesQualityCalculator(final Predicate<Item> isBackstagePassesPredicate) {
        super(isBackstagePassesPredicate);
    }

    @Override
    protected int calculateQuality(final Item item) {
        if (!isExpired(item)) {
            int result = item.quality + 1;
            if (hasLessThan10DaysLeft(item)) {
                result++;
                if (hasLessThan5DaysLeft(item)) {
                    result++;
                }
            }
            return result;
        } else {
            return 0;
        }
    }

    private boolean isExpired(final Item item) {
        return item.sellIn < 0;
    }

    private boolean hasLessThan10DaysLeft(final Item item) {
        return item.sellIn < 10;
    }

    private boolean hasLessThan5DaysLeft(final Item item) {
        return item.sellIn < 5;
    }
}
