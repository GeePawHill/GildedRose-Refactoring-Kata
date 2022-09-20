package com.gildedrose

const val SULFURUS_NAME = "Sulfuras, Hand of Ragnaros"
const val BRIE_NAME = "Aged Brie"
const val BACKSTAGE_NAME = "Backstage passes to a TAFKAL80ETC concert"

const val MAX_NORMAL_QUALITY = 50

const val BACKSTAGE_FIRST_UPGRADE = 11
const val BACKSTAGE_SECOND_UPGRADE = 6

class GildedRose(var items: Array<Item>) {

    fun updateQuality() {
        for (i in items.indices) {
            if (items[i].name != BRIE_NAME && items[i].name != BACKSTAGE_NAME) {
                if (items[i].quality > 0) {
                    if (items[i].name != SULFURUS_NAME) {
                        items[i].quality = items[i].quality - 1
                    }
                }
            } else {
                if (items[i].quality < MAX_NORMAL_QUALITY) {
                    items[i].quality = items[i].quality + 1

                    if (items[i].name == BACKSTAGE_NAME) {
                        if (items[i].sellIn < BACKSTAGE_FIRST_UPGRADE) {
                            if (items[i].quality < MAX_NORMAL_QUALITY) {
                                items[i].quality = items[i].quality + 1
                            }
                        }

                        if (items[i].sellIn < BACKSTAGE_SECOND_UPGRADE) {
                            if (items[i].quality < MAX_NORMAL_QUALITY) {
                                items[i].quality = items[i].quality + 1
                            }
                        }
                    }
                }
            }

            if (items[i].name != SULFURUS_NAME) {
                items[i].sellIn = items[i].sellIn - 1
            }

            if (items[i].sellIn < 0) {
                if (items[i].name != BRIE_NAME) {
                    if (items[i].name != BACKSTAGE_NAME) {
                        if (items[i].quality > 0) {
                            if (items[i].name != SULFURUS_NAME) {
                                items[i].quality = items[i].quality - 1
                            }
                        }
                    } else {
                        items[i].quality = items[i].quality - items[i].quality
                    }
                } else {
                    if (items[i].quality < MAX_NORMAL_QUALITY) {
                        items[i].quality = items[i].quality + 1
                    }
                }
            }
        }
    }

}

