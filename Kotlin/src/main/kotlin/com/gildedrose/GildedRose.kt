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
                quality += 1
                if (sellIn < BACKSTAGE_FIRST_UPGRADE) quality += 1
                if (sellIn < BACKSTAGE_SECOND_UPGRADE) quality += 1
            }
            if (quality > MAX_NORMAL_QUALITY) quality = MAX_NORMAL_QUALITY
        }
    }

    fun updateBrie(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) quality += 1
            quality += 1
            if (quality > MAX_NORMAL_QUALITY) quality = MAX_NORMAL_QUALITY
        }
    }

    fun updateGeneric(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) quality -= 1
            quality -= 1
            if (quality < 0) quality = 0
        }
    }

    fun updateConjured(item: Item) {
        with(item) {
            sellIn -= 1
            if (sellIn < 0) quality -= 2
            quality -= 2
            if (quality < 0) quality = 0
        }
    }
}

