package com.gildedrose

import com.gildedrose.strategy.ItemStrategy
import com.gildedrose.strategy.ItemStrategy.*


class GildedRose(var items: Array<Item>) {

    private fun getStrategy(name: String): ItemStrategy {
        return when (name) {
            "Aged Brie" -> AgedBrieItemStrategy
            "Sulfuras, Hand of Ragnaros" -> SulfurasItemStrategy
            "Backstage passes to a TAFKAL80ETC concert" -> BackstageItemStrategy
            "Conjured Mana Cake" -> ConjuredItemStrategy
            else -> NormalItemStrategy
        }
    }

    fun updateQuality() {
        for (i in items.indices) {
            val strategy = getStrategy(items[i].name)
            strategy.updateItem(items[i])
        }
    }

}

