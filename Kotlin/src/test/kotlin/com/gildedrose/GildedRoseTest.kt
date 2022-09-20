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

    @Test
    fun `post-date backstage has quality 0`() {
        assertBeforeAndAfter(BACKSTAGE_NAME,0,30,-1,0)
    }

    @Test
    fun `pre-date backstage quality goes up by 1`() {
        assertBeforeAndAfter(BACKSTAGE_NAME,20,30,19,31)
    }

    @Test
    fun `pre-date backstage quality goes up by 2 at 10 days out`() {
        assertBeforeAndAfter(BACKSTAGE_NAME,10,30,9,32)
    }

    @Test
    fun `pre-date backstage quality goes up by 3 at 5 days out`() {
        assertBeforeAndAfter(BACKSTAGE_NAME,5,30,4,33)
    }

    @Test
    fun `pre-date backstage quality never goes past MAX_NORMAL_QUALITY`() {
        assertBeforeAndAfter(BACKSTAGE_NAME,5, MAX_NORMAL_QUALITY,4, MAX_NORMAL_QUALITY)
    }

    @Test
    fun `pre-date brie goes up 1 in quality`() {
        assertBeforeAndAfter(BRIE_NAME,1,10,0,11)
    }

    @Test
    fun `post-date brie quality goes up by 2`() {
        assertBeforeAndAfter(BRIE_NAME,0, 20,-1, 22)
    }

    @Test
    fun `post-date brie quality never goes past MAX_NORMAL_QUALITY`() {
        assertBeforeAndAfter(BRIE_NAME,0, MAX_NORMAL_QUALITY,-1, MAX_NORMAL_QUALITY)
    }

    @Test
    fun `brie quality never goes past MAX_NORMAL_QUALITY`() {
        assertBeforeAndAfter(BRIE_NAME,5, MAX_NORMAL_QUALITY,4, MAX_NORMAL_QUALITY)
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


