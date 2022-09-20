package com.gildedrose

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class GildedRoseTest {

    @Test
    fun `sulfuras never changes`() {
        assertBeforeAndAfter(SULFURUS_NAME, Int.MAX_VALUE, 80, Int.MAX_VALUE,80)
    }

    @Test
    fun `sulfuras never changes even with weird values`() {
        // NOTE: Gilded rose does not enforce quality or sellin for sulfuras
        assertBeforeAndAfter(SULFURUS_NAME,-1,-3,-1,-3)
    }

    @Test
    fun `generic pre-date items lose quality by 1`() {
        assertBeforeAndAfter(GENERIC_NAME,10,5,9,4)
    }

    @Test
    fun `generic pre-date items quality bottoms out at 0`() {
        assertBeforeAndAfter(GENERIC_NAME,10,0,9,0)
    }

    @Test
    fun `generic post-date items quality drops by 2 at sellin day`() {
        assertBeforeAndAfter(GENERIC_NAME,0,10,-1,8)
    }

    @Test
    fun `generic post-date items quality bottoms out at 0 even after sellin day`() {
        assertBeforeAndAfter(GENERIC_NAME,-5,0,-6,0)
    }

    fun assertBeforeAndAfter(beforeName:String,beforeSellin:Int,beforeQuality:Int,afterSellin:Int,afterQuality:Int) {
        updateQuality(Item(beforeName,beforeSellin,beforeQuality)).apply {
            assertThat(name).isEqualTo(beforeName)
            assertThat(sellIn).isEqualTo(afterSellin)
            assertThat(quality).isEqualTo(afterQuality)
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


