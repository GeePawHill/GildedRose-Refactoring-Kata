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

class GildedRose(var items: Array<Item>) {

    private val sulfurusUpdater = SulfurusUpdater()
    private val brieUpdater = BrieUpdater()
    private val backstageUpdater = BackstageUpdater()

    fun updateQuality() {
        for (item in items) {
            updateQuality(item)
        }
    }

    fun updateQuality(item: Item) {
        with(item) {
            if (name == SULFURUS_NAME) {
                sulfurusUpdater.updateQuality(item)
                return
            }
            if (name == BRIE_NAME) {
                brieUpdater.updateQuality(item)
                return
            }
            if (name == BACKSTAGE_NAME) {
                backstageUpdater.updateQuality(item)
                return
            }
            if (name != BRIE_NAME && name != BACKSTAGE_NAME) {
                if (quality > 0) {
                    if (name != SULFURUS_NAME) {
                        quality = quality - 1
                    }
                }
            } else {
                if (quality < MAX_NORMAL_QUALITY) {
                    quality = quality + 1

                    if (name == BACKSTAGE_NAME) {
                        if (sellIn < BACKSTAGE_FIRST_UPGRADE) {
                            if (quality < MAX_NORMAL_QUALITY) {
                                quality = quality + 1
                            }
                        }

                        if (sellIn < BACKSTAGE_SECOND_UPGRADE) {
                            if (quality < MAX_NORMAL_QUALITY) {
                                quality = quality + 1
                            }
                        }
                    }
                }
            }

            if (name != SULFURUS_NAME) {
                sellIn = sellIn - 1
            }

            if (sellIn < 0) {
                if (name != BRIE_NAME) {
                    if (name != BACKSTAGE_NAME) {
                        if (quality > 0) {
                            if (name != SULFURUS_NAME) {
                                quality = quality - 1
                            }
                        }
                    } else {
                        quality = quality - quality
                    }
                } else {
                    if (quality < MAX_NORMAL_QUALITY) {
                        quality = quality + 1
                    }
                }
            }
        }
    }
}

