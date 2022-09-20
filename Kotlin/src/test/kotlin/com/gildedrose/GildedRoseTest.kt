package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

    @Test
    fun `sulfuras never changes`() {
        val items = arrayOf(Item(SULFURUS_NAME, Int.MAX_VALUE, 80))
        val app = GildedRose(items)
        app.updateQuality()
        assertThat(items[0].name).isEqualTo(SULFURUS_NAME)
        assertThat(items[0].sellIn).isEqualTo(Int.MAX_VALUE)
        assertThat(items[0].quality).isEqualTo(80)
    }

}


