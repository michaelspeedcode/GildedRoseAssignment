package com.gildedrose

import org.junit.Assert.assertEquals
import org.junit.Ignore
import org.junit.Test

class GildedRoseTest {

    @Test
    fun foo() {
        val items = arrayOf(Item("foo", 0, 0))
        val app = GildedRose(items)
        app.updateQuality()
        assertEquals("foo", app.items[0].name)
    }

    @Test
    fun item_beforeSellByDateTest() {
        // At the end of each day our system lowers both values for every item

        val initialSellIn = 10
        val initialQuality = 15

        val items = arrayOf(Item("normal", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1 until initialSellIn) {
            app.updateQuality()
            assertEquals(initialSellIn - 1 * i, app.items[0].sellIn)
            assertEquals(initialQuality - 1 * i, app.items[0].quality)
        }
    }

    @Test
    fun item_afterSellByDateTest() {
        // Once the sell by date has passed, Quality degrades twice as fast

        val initialSellIn = 0
        val initialQuality = 25

        val items = arrayOf(Item("normal", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(initialQuality - 2 * i, app.items[0].quality)
        }
    }

    @Test
    fun agedBrie_beforeSellByDateTest() {
        val initialSellIn = 10
        val initialQuality = 15

        val items = arrayOf(Item("Aged Brie", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1 until initialSellIn) {
            app.updateQuality()
            assertEquals(initialSellIn - i, app.items[0].sellIn)
            assertEquals(50.coerceAtMost(initialQuality + i), app.items[0].quality)
        }
    }

    @Test
    fun agedBrie_afterSellByDateTest() {
        //"Aged Brie" actually increases in Quality the older it gets

        val initialSellIn = 0
        val initialQuality = 20

        val items = arrayOf(Item("Aged Brie", initialSellIn
                , initialQuality))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(50.coerceAtMost(initialQuality + 2 * i), app.items[0].quality)
        }
    }

    @Test
    fun sulfurasTest() {
        // Sulfuras doesn't age

        val initialSellIn = 0
        val initialQuality = 80

        val items = arrayOf(Item("Sulfuras, Hand of Ragnaros", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(0, app.items[0].sellIn)
            assertEquals(initialQuality, app.items[0].quality)
        }
    }

    @Test
    fun backstagePass_afterSellByDateTest() {
        //	Quality drops to 0 after the concert

        val initialSellIn = 0
        val initialQuality = 4

        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1..30) {
            app.updateQuality()
            assertEquals(-i, app.items[0].sellIn)
            assertEquals(0, app.items[0].quality)
        }
    }

    @Test
    fun backstagePass_within10DaysTest() {
        //	Quality increases by 2 when there are 10 days or less

        val initialSellIn = 10
        val initialQuality = 40

        val items = arrayOf(Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1..10) {
            app.updateQuality()
            assertEquals(initialSellIn - i, app.items[0].sellIn)
            assertEquals(50.coerceAtMost(initialQuality + 2 * i), app.items[0].quality)
        }
    }

    @Test
    fun backstagePass_within5DaysTest() {
        // ... and by 3 when there are 5 days or less but

        val initialSellIn = 5
        val initialQuality = 1

        val items = arrayOf<Item>(Item("Backstage passes to a TAFKAL80ETC concert", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1..initialSellIn) {
            app.updateQuality()
            assertEquals(initialSellIn - i, app.items[0].sellIn)
            assertEquals(50.coerceAtMost(initialQuality + 3 * i), app.items[0].quality)
        }
    }

    @Test
    @Ignore
    fun conjuredItemsTest() {
        // "Conjured" items degrade in Quality twice as fast as normal items

        val initialSellIn = 20
        val initialQuality = 25

        val items = arrayOf(Item("Conjured Mana Cake", initialSellIn, initialQuality))
        val app = GildedRose(items)
        for (i in 1 until initialSellIn) {
            app.updateQuality()
            assertEquals((initialSellIn - 1) - i, app.items[0].sellIn)
            assertEquals((initialQuality - 2) - i, app.items[0].quality)
        }
    }

}
