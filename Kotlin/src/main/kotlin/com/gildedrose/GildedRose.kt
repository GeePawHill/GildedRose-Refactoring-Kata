package com.gildedrose

const val SULFURUS_NAME = "Sulfuras, Hand of Ragnaros"
const val BRIE_NAME = "Aged Brie"
const val BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert"

const val MAX_NORMAL_QUALITY = 50

const val BACKSTAGE_FIRST_UPGRADE = 11
const val BACKSTAGE_SECOND_UPGRADE = 6

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (item in items) {
            updateQuality(item)
        }
    }

    fun updateQuality(item: Item) {
        if (item.name != BRIE_NAME && item.name != BACKSTAGE_NAME) {
            if (item.quality > 0) {
                if (item.name != SULFURUS_NAME) {
                    item.quality = item.quality - 1
                }
            }
        } else {
            if (item.quality < MAX_NORMAL_QUALITY) {
                item.quality = item.quality + 1

                if (item.name == BACKSTAGE_NAME) {
                    if (item.sellIn < BACKSTAGE_FIRST_UPGRADE) {
                        if (item.quality < MAX_NORMAL_QUALITY) {
                            item.quality = item.quality + 1
                        }
                    }

                    if (item.sellIn < BACKSTAGE_SECOND_UPGRADE) {
                        if (item.quality < MAX_NORMAL_QUALITY) {
                            item.quality = item.quality + 1
                        }
                    }
                }
            }
        }

        if (item.name != SULFURUS_NAME) {
            item.sellIn = item.sellIn - 1
        }

        if (item.sellIn < 0) {
            if (item.name != BRIE_NAME) {
                if (item.name != BACKSTAGE_NAME) {
                    if (item.quality > 0) {
                        if (item.name != SULFURUS_NAME) {
                            item.quality = item.quality - 1
                        }
                    }
                } else {
                    item.quality = item.quality - item.quality
                }
            } else {
                if (item.quality < MAX_NORMAL_QUALITY) {
                    item.quality = item.quality + 1
                }
            }
        }
    }

}

