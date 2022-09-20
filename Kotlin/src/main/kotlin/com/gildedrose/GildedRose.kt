package com.gildedrose

const val SULFURUS_NAME = "Sulfuras, Hand of Ragnaros"
const val BRIE_NAME = "Aged Brie"
const val BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert"
const val CONJURED_NAME = "Conjured"

const val MAX_NORMAL_QUALITY = 50

const val BACKSTAGE_FIRST_UPGRADE = 11
const val BACKSTAGE_SECOND_UPGRADE = 6

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        items.forEach { updateQuality(it) }
    }

    fun updateQuality(item: Item) {
        with(item) {
            when (name) {
                SULFURUS_NAME -> return
                BRIE_NAME -> updateBrie(item)
                BACKSTAGE_NAME -> updateBackstage(item)
                CONJURED_NAME -> updateConjured(item)
                else -> updateGeneric(item)
            }
        }
    }

    fun updateBackstage(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) quality = 0
            else {
                adjustQuality(item, 1)
                if (sellIn < BACKSTAGE_FIRST_UPGRADE) adjustQuality(item, 1)
                if (sellIn < BACKSTAGE_SECOND_UPGRADE) adjustQuality(item, 1)
            }
        }
    }

    fun updateBrie(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) adjustQuality(item, 1)
            adjustQuality(item, 1)
        }
    }

    fun updateGeneric(item: Item) {
        with(item) {
            sellIn -= 1
            adjustQuality(item, -1)
            if (sellIn < 0) adjustQuality(item, -1)
        }
    }

    fun updateConjured(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) adjustQuality(item, -2)
            adjustQuality(this, -2)
        }
    }

    fun adjustQuality(item: Item, increment: Int) {
        item.quality += increment
        if (item.quality > MAX_NORMAL_QUALITY) item.quality = MAX_NORMAL_QUALITY
        if (item.quality < 0) item.quality = 0
    }
}

