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
                "normal" -> updateNormal(items[i])
                "Aged Brie" -> updateBrie(items[i])
                "Backstage passes to a TAFKAL80ETC concert" -> updateBackstagePass(items[i])
            }

            return

            if (items[i].name != "Aged Brie" && items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                if (items[i].quality > 0) {
                    if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                        items[i].quality -= 1
                    }
                }
            } else {
                if (items[i].quality < 50) {
                    items[i].quality += 1

                    if (items[i].name == "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].sellIn < 11) {
                            if (items[i].quality < 50) {
                                items[i].quality += 1
                            }
                        }

                        if (items[i].sellIn < 6) {
                            if (items[i].quality < 50) {
                                items[i].quality += 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != "Aged Brie") {
                    if (items[i].name != "Backstage passes to a TAFKAL80ETC concert") {
                        if (items[i].quality > 0) {
                            if (items[i].name != "Sulfuras, Hand of Ragnaros") {
                                items[i].quality -= 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < 50) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}

