package com.kapouter.pileapp.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PlantConverters {
    @TypeConverter
    fun imageListToString(images: List<Image>?): String? {
        if (images == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.toJson(images, type)
    }

    @TypeConverter
    fun stringToImageList(json: String?): List<Image>? {
        if (json == null) return null
        val gson = Gson()
        val type = object : TypeToken<List<Image>>() {}.type
        return gson.fromJson(json, type)
    }

    @TypeConverter
    fun binaryToString(binary: Binary?): String? {
        if (binary == null) return null
        return binary.name
    }

    @TypeConverter
    fun stringToBinary(json: String?): Binary? {
        if (json == null) return null
        return Binary.valueOf(json)
    }

    @TypeConverter
    fun levelToString(level: Level?): String? {
        if (level == null) return null
        return level.name
    }

    @TypeConverter
    fun stringToLevel(json: String?): Level? {
        if (json == null) return null
        return Level.valueOf(json)
    }

    @TypeConverter
    fun yearPeriodToString(yearPeriod: YearPeriod?): String? {
        if (yearPeriod == null) return null
        return yearPeriod.name
    }

    @TypeConverter
    fun stringToYearPeriod(json: String?): YearPeriod? {
        if (json == null) return null
        return YearPeriod.valueOf(json)
    }

    @TypeConverter
    fun rateToString(rate: Rate?): String? {
        if (rate == null) return null
        return rate.name
    }

    @TypeConverter
    fun stringToRate(json: String?): Rate? {
        if (json == null) return null
        return Rate.valueOf(json)
    }

    @TypeConverter
    fun lifespanToString(lifespan: Lifespan?): String? {
        if (lifespan == null) return null
        return lifespan.name
    }

    @TypeConverter
    fun stringToLifespan(json: String?): Lifespan? {
        if (json == null) return null
        return Lifespan.valueOf(json)
    }

    @TypeConverter
    fun toxicityToString(toxicity: Toxicity?): String? {
        if (toxicity == null) return null
        return toxicity.name
    }

    @TypeConverter
    fun stringToToxicity(json: String?): Toxicity? {
        if (json == null) return null
        return Toxicity.valueOf(json)
    }

    @TypeConverter
    fun bloomPeriodToString(bloomPeriod: BloomPeriod?): String? {
        if (bloomPeriod == null) return null
        return bloomPeriod.name
    }

    @TypeConverter
    fun stringToBloomPeriod(json: String?): BloomPeriod? {
        if (json == null) return null
        return BloomPeriod.valueOf(json)
    }

    @TypeConverter
    fun growthPeriodToString(growthPeriod: GrowthPeriod?): String? {
        if (growthPeriod == null) return null
        return growthPeriod.name
    }

    @TypeConverter
    fun stringToGrowthPeriod(json: String?): GrowthPeriod? {
        if (json == null) return null
        return GrowthPeriod.valueOf(json)
    }
}