package com.kapouter.pileapp.model

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Before
import org.junit.Test

class PlantConvertersTest {

    private lateinit var converter: PlantConverters

    @Before
    fun setup() {
        converter = PlantConverters()
    }

    // Image List

    @Test
    fun testImageListToStringWithNull() {
        val imageList: List<Image>? = null
        val result = converter.imageListToString(imageList)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testImageListToStringWithEmptyList() {
        val imageList: List<Image>? = listOf()
        val result = converter.imageListToString(imageList)
        val expectedValue: String? = "[]"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testImageListToStringWithImageList() {
        val img1 = Image("img1.png")
        val img2 = Image("img2.png")
        val imageList: List<Image>? = listOf(img1, img2)
        val result = converter.imageListToString(imageList)
        val expectedValue: String? = "[{\"url\":\"img1.png\"},{\"url\":\"img2.png\"}]"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToImageListWithNull() {
        val json: String? = null
        val result = converter.stringToImageList(json)
        val expectedValue: List<Image>? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToImageListWithEmptyList() {
        val imageList: String? = "[]"
        val result = converter.stringToImageList(imageList)
        val expectedValue: List<Image>? = listOf()
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToImageListWithImageList() {
        val json: String? = "[{url: 'img1.png'},{url: 'img2.png'}]"
        val result = converter.stringToImageList(json)
        val img1 = Image("img1.png")
        val img2 = Image("img2.png")
        val expectedValue: List<Image>? = listOf(img1, img2)
        assertThat(result, equalTo(expectedValue))
    }

    // Binary

    @Test
    fun testBinaryToStringWithNull() {
        val binary: Binary? = null
        val result = converter.binaryToString(binary)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testBinaryToStringWithBinary() {
        val binary: Binary? = Binary.YES
        val result = converter.binaryToString(binary)
        val expectedValue: String? = "YES"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToBinaryWithNull() {
        val binary: String? = null
        val result = converter.stringToBinary(binary)
        val expectedValue: Binary? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToBinaryWithBinary() {
        val binary: String? = "YES"
        val result = converter.stringToBinary(binary)
        val expectedValue: Binary? = Binary.valueOf("YES")
        assertThat(result, equalTo(expectedValue))
    }

    // Level

    @Test
    fun testLevelToStringWithNull() {
        val level: Level? = null
        val result = converter.levelToString(level)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testLevelToStringWithLevel() {
        val level: Level? = Level.MEDIUM
        val result = converter.levelToString(level)
        val expectedValue: String? = "MEDIUM"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToLevelWithNull() {
        val level: String? = null
        val result = converter.stringToLevel(level)
        val expectedValue: Level? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToLevelWithLevel() {
        val level: String? = "MEDIUM"
        val result = converter.stringToLevel(level)
        val expectedValue: Level? = Level.MEDIUM
        assertThat(result, equalTo(expectedValue))
    }

    // Year Period

    @Test
    fun testYearPeriodToStringWithNull() {
        val yearPeriod: YearPeriod? = null
        val result = converter.yearPeriodToString(yearPeriod)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testYearPeriodToStringWithYearPeriod() {
        val yearPeriod: YearPeriod? = YearPeriod.YEAR_ROUND
        val result = converter.yearPeriodToString(yearPeriod)
        val expectedValue: String? = "YEAR_ROUND"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToYearPeriodWithNull() {
        val yearPeriod: String? = null
        val result = converter.stringToYearPeriod(yearPeriod)
        val expectedValue: YearPeriod? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToYearPeriodWithYearPeriod() {
        val yearPeriod: String? = "YEAR_ROUND"
        val result = converter.stringToYearPeriod(yearPeriod)
        val expectedValue: YearPeriod? = YearPeriod.YEAR_ROUND
        assertThat(result, equalTo(expectedValue))
    }

    // Rate

    @Test
    fun testRateToStringWithNull() {
        val rate: Rate? = null
        val result = converter.rateToString(rate)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testRateToStringWithRate() {
        val rate: Rate? = Rate.MODERATE
        val result = converter.rateToString(rate)
        val expectedValue: String? = "MODERATE"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToRateWithNull() {
        val rate: String? = null
        val result = converter.stringToRate(rate)
        val expectedValue: Rate? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToRateWithRate() {
        val rate: String? = "MODERATE"
        val result = converter.stringToRate(rate)
        val expectedValue: Rate? = Rate.MODERATE
        assertThat(result, equalTo(expectedValue))
    }

    // Lifespan

    @Test
    fun testLifespanToStringWithNull() {
        val lifespan: Lifespan? = null
        val result = converter.lifespanToString(lifespan)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testLifespanToStringWithLifespan() {
        val lifespan: Lifespan? = Lifespan.MODERATE
        val result = converter.lifespanToString(lifespan)
        val expectedValue: String? = "MODERATE"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToLifespanWithNull() {
        val lifespan: String? = null
        val result = converter.stringToLifespan(lifespan)
        val expectedValue: Lifespan? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToLifespanWithLifespan() {
        val lifespan: String? = "MODERATE"
        val result = converter.stringToLifespan(lifespan)
        val expectedValue: Lifespan? = Lifespan.MODERATE
        assertThat(result, equalTo(expectedValue))
    }

    // Toxicity

    @Test
    fun testToxicityToStringWithNull() {
        val toxicity: Toxicity? = null
        val result = converter.toxicityToString(toxicity)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testToxicityToStringWithToxicity() {
        val toxicity: Toxicity? = Toxicity.MODERATE
        val result = converter.toxicityToString(toxicity)
        val expectedValue: String? = "MODERATE"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToToxicityWithNull() {
        val toxicity: String? = null
        val result = converter.stringToToxicity(toxicity)
        val expectedValue: Toxicity? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToToxicityWithToxicity() {
        val toxicity: String? = "MODERATE"
        val result = converter.stringToToxicity(toxicity)
        val expectedValue: Toxicity? = Toxicity.MODERATE
        assertThat(result, equalTo(expectedValue))
    }

    // Bloom Period

    @Test
    fun testBloomPeriodToStringWithNull() {
        val bloomPeriod: BloomPeriod? = null
        val result = converter.bloomPeriodToString(bloomPeriod)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testBloomPeriodToStringWithBloomPeriod() {
        val bloomPeriod: BloomPeriod? = BloomPeriod.SPRING
        val result = converter.bloomPeriodToString(bloomPeriod)
        val expectedValue: String? = "SPRING"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToBloomPeriodWithNull() {
        val bloomPeriod: String? = null
        val result = converter.stringToBloomPeriod(bloomPeriod)
        val expectedValue: BloomPeriod? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToBloomPeriodWithBloomPeriod() {
        val bloomPeriod: String? = "SPRING"
        val result = converter.stringToBloomPeriod(bloomPeriod)
        val expectedValue: BloomPeriod? = BloomPeriod.SPRING
        assertThat(result, equalTo(expectedValue))
    }

    // Growth Period

    @Test
    fun testGrowthPeriodToStringWithNull() {
        val growthPeriod: GrowthPeriod? = null
        val result = converter.growthPeriodToString(growthPeriod)
        val expectedValue: String? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testGrowthPeriodToStringWithGrowthPeriod() {
        val growthPeriod: GrowthPeriod? = GrowthPeriod.SPRING
        val result = converter.growthPeriodToString(growthPeriod)
        val expectedValue: String? = "SPRING"
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToGrowthPeriodWithNull() {
        val growthPeriod: String? = null
        val result = converter.stringToGrowthPeriod(growthPeriod)
        val expectedValue: GrowthPeriod? = null
        assertThat(result, equalTo(expectedValue))
    }

    @Test
    fun testStringToGrowthPeriodWithGrowthPeriod() {
        val growthPeriod: String? = "SPRING"
        val result = converter.stringToGrowthPeriod(growthPeriod)
        val expectedValue: GrowthPeriod? = GrowthPeriod.SPRING
        assertThat(result, equalTo(expectedValue))
    }

}