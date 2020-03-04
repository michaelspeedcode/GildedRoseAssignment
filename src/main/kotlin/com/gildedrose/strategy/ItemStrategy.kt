package com.gildedrose.strategy

import com.gildedrose.Item

sealed class ItemStrategy {

    open fun updateItem(item: Item) {
        with(item) {
            sellIn -= 1;
            if (quality == 0) return

            quality -= 1;

            if (sellIn <= 0) {
                quality -= 1
            }
        }
    }

    object AgedBrieItemStrategy : ItemStrategy() {
        override fun updateItem(item: Item) {
            with(item) {
                sellIn -= 1
                if (quality >= 50) return

                quality += 1

                if (sellIn <= 0) {
                    quality += 1
                }
            }
        }
    }

    object SulfurasItemStrategy : ItemStrategy() {
        override fun updateItem(item: Item) {
            // Sulfura stays the same
        }
    }

    object BackstageItemStrategy : ItemStrategy() {
        override fun updateItem(item: Item) {
            with(item) {
                sellIn -= 1

                if (quality >= 50) return

                if (sellIn < 0) {
                    quality = 0
                    return
                }

                quality += 1

                if (sellIn < 10)
                    quality += 1

                if (sellIn < 5)
                    quality += 1

                if (quality > 50) quality = 50

            }
        }
    }

    object ConjuredItemStrategy : ItemStrategy() {
        override fun updateItem(item: Item) {
            super.updateItem(item)
        }
    }

    object NormalItemStrategy : ItemStrategy() {
        override fun updateItem(item: Item) {
            super.updateItem(item)
        }
    }
}
