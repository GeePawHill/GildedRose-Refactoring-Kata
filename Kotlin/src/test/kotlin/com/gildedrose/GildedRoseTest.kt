package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

    @Test
    fun `sulfuras never changes`() {
        val item = updateQuality(Item(SULFURUS_NAME, Int.MAX_VALUE, 80))
        with(item) {
            assertThat(name).isEqualTo(SULFURUS_NAME)
            assertThat(sellIn).isEqualTo(Int.MAX_VALUE)
            assertThat(quality).isEqualTo(80)
        }
    }

    @Test
    fun `sulfuras never changes even with weird values`() {
        val item = updateQuality(Item(SULFURUS_NAME, -1, -3))
        with(item) {
            assertThat(name).isEqualTo(SULFURUS_NAME)
            assertThat(sellIn).isEqualTo(-1)
            assertThat(quality).isEqualTo(-3)
        }
    }

    @Test
    fun `generic items lose quality by 1`() {
        val item = updateQuality(Item(GENERIC_NAME, 10,5))
        with(item) {
            assertThat(name).isEqualTo(GENERIC_NAME)
            assertThat(sellIn).isEqualTo(9)
            assertThat(quality).isEqualTo(4)
        }
    }


    fun updateQuality(item:Item):Item {
        val items = arrayOf(item)
        val app = GildedRose(items)
        app.updateQuality()
        return app.items[0]
    }

    companion object {
        const val GENERIC_NAME = "Some generic product"
    }

}


