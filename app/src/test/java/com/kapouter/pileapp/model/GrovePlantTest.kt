package com.kapouter.pileapp.model

import org.hamcrest.Matchers.equalTo
import org.junit.Assert.assertThat
import org.junit.Test


class GrovePlantTest {

    @Test
    fun testFindFirstNonSvgUrlWithNull() {
        val list: List<Image>? = null
        val result: String? = list.findFirstNonSvgUrl()
        val expectedResult: String? = null
        assertThat(result, equalTo(expectedResult))
    }

    @Test
    fun testFindFirstNonSvgUrlWithEmptyList() {
        val list: List<Image>? = listOf()
        val result: String? = list.findFirstNonSvgUrl()
        val expectedResult: String? = null
        assertThat(result, equalTo(expectedResult))
    }

    @Test
    fun testFindFirstNonSvgUrlWithSvgSingleList() {
        val svg = Image("img.svg")
        val list: List<Image>? = listOf(svg)
        val result: String? = list.findFirstNonSvgUrl()
        val expectedResult: String? = svg.url
        assertThat(result, equalTo(expectedResult))
    }

    @Test
    fun testFindFirstNonSvgUrlWithPngOnlyList() {
        val png1 = Image("img1.png")
        val png2 = Image("img2.png")
        val png3 = Image("img3.png")
        val list: List<Image>? = listOf(png1, png2, png3)
        val result: String? = list.findFirstNonSvgUrl()
        val expectedResult: String? = png1.url
        assertThat(result, equalTo(expectedResult))
    }

    @Test
    fun testFindFirstNonSvgUrlWithMixedList() {
        val svg1 = Image("img1.svg")
        val svg2 = Image("img2.svg")
        val png1 = Image("img1.png")
        val png2 = Image("img2.png")
        val png3 = Image("img3.png")
        val list: List<Image>? = listOf(svg1, png1, svg2, png2, png3)
        val result: String? = list.findFirstNonSvgUrl()
        val expectedResult: String? = png1.url
        assertThat(result, equalTo(expectedResult))
    }
}