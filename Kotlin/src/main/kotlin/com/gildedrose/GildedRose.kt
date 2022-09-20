package com.gildedrose

const val SULFURUS_NAME = "Sulfuras, Hand of Ragnaros"
const val BRIE_NAME = "Aged Brie"
const val BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert"

const val MAX_NORMAL_QUALITY = 50

const val BACKSTAGE_FIRST_UPGRADE = 11
const val BACKSTAGE_SECOND_UPGRADE = 6

class SulfurusUpdater() {
    fun updateQuality(item: Item) {
    }
}

class BrieUpdater() {
    fun updateQuality(item: Item) {
        with(item) {
            sellIn -= 1
            quality += 1
            if (sellIn < 0) quality += 1
            if (quality > MAX_NORMAL_QUALITY) quality = MAX_NORMAL_QUALITY
        }
    }
}

class BackstageUpdater {
    fun updateQuality(item: Item) {
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
}

class GenericUpdater {
    fun updateQuality(item: Item) {
        with(item) {
            sellIn -= 1
            quality -= 1
            if (sellIn < 0) quality -= 1
            if (quality < 0) quality = 0
        }
    }
}

class GildedRose(var items: Array<Item>) {

    private val sulfurusUpdater = SulfurusUpdater()
    private val brieUpdater = BrieUpdater()
    private val backstageUpdater = BackstageUpdater()
    private val genericUpdater = GenericUpdater()

    fun updateQuality() {
        for (item in items) {
            updateQuality(item)
        }
    }

    fun updateQuality(item: Item) {
        with(item) {
            when (name) {
                SULFURUS_NAME -> sulfurusUpdater.updateQuality(item)
                BRIE_NAME -> brieUpdater.updateQuality(item)
                BACKSTAGE_NAME -> backstageUpdater.updateQuality(item)
                else -> genericUpdater.updateQuality(item)
            }
        }
    }
}

