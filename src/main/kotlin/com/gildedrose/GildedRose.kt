package com.gildedrose

class GildedRose(var items: Array<Item>) {

    private fun updateBrie(item: Item) {
        with(item) {
            sellIn -= 1
            if (quality >= 50) return

            quality += 1

            if (sellIn <= 0) {
                quality += 1
            }
        }
    }

    private fun updateSulfuras(item: Item) {
    }

    private fun updateBackstagePass(item: Item) {
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

    private fun updateNormal(item: Item) {
        with(item) {
            sellIn -= 1;
            if (quality == 0) return

            quality -= 1;

            if (sellIn <= 0) {
                quality -= 1
            }
        }
    }

    fun updateQuality() {
        for (i in items.indices) {

            when (items[i].name) {
                "Aged Brie" -> updateBrie(items[i])
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass(items[i])
                "Sulfuras, Hand of Ragnaros" -> updateSulfuras(items[i])
                else -> updateNormal(items[i])
            }
        }
    }

}

