package com.kapouter.pileapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.SerializedName

@Entity(tableName = "grovePlants")
data class GrovePlant(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("common_name") val name: String?,
    @SerializedName("scientific_name") val scientificName: String,
    @SerializedName("link") val link: String?,
    @TypeConverters(PlantConverters::class) @SerializedName("images") val images: List<Image>?,
    @SerializedName("duration") val duration: String?,
    @Embedded(prefix = "main_species_") @SerializedName("main_species") val mainSpecies: MainSpecies?
)

data class Image(val url: String)

fun List<Image>?.findFirstNonSvgUrl(): String? {
    if (this == null || this.isEmpty()) return null
    return this.find { !it.url.contains("svg", true) }?.url ?: this[0].url
}

data class MainSpecies(
    @Embedded(prefix = "specifications_") @SerializedName("specifications") val specifications: MainSpeciesSpecifications?,
    @Embedded(prefix = "seed_") @SerializedName("seed") val seed: Seed?,
    @Embedded(prefix = "propagation_") @SerializedName("propagation") val propagation: Propagation?,
    @Embedded(prefix = "soils_adaptation_") @SerializedName("soils_adaptation") val soilsAdaptation: SoilsAdaptation?
)

data class MainSpeciesSpecifications(
    @TypeConverters(PlantConverters::class) @SerializedName("toxicity") val toxicity: Toxicity?,
    @TypeConverters(PlantConverters::class) @SerializedName("regrowth_rate") val regrowthRate: String?,
    @TypeConverters(PlantConverters::class) @SerializedName("growth_rate") val growthRate: Rate?,
    @TypeConverters(PlantConverters::class) @SerializedName("growth_period") val growthPeriod: GrowthPeriod?,
    @SerializedName("growth_habit") val growthHabit: String?,
    @SerializedName("growth_form") val growthForm: String?,
    @TypeConverters(PlantConverters::class) @SerializedName("lifespan") val lifespan: Lifespan?,
    @SerializedName("leaf_retention") val leafRetention: Boolean?,
    @TypeConverters(PlantConverters::class) @SerializedName("bloat") val bloat: Level?
)

data class Seed(
    @TypeConverters(PlantConverters::class) @SerializedName("vegetative_spread_rate") val vegetativeSpreadRate: Rate?,
    @TypeConverters(PlantConverters::class) @SerializedName("seedling_vigor") val seedlingVigor: Level?,
    @TypeConverters(PlantConverters::class) @SerializedName("seed_spread_rate") val seedSpreadRate: Rate?,
    @TypeConverters(PlantConverters::class) @SerializedName("bloom_period") val bloomPeriod: BloomPeriod?
)

data class Propagation(
    @SerializedName("tubers") val tubers: Boolean?,
    @SerializedName("sprigs") val sprigs: Boolean?,
    @SerializedName("sod") val sod: Boolean?,
    @SerializedName("seed") val seed: Boolean?,
    @SerializedName("cuttings") val cuttings: Boolean?,
    @SerializedName("corms") val corms: Boolean?,
    @SerializedName("container") val container: Boolean?,
    @SerializedName("bulbs") val bulbs: Boolean?,
    @SerializedName("bare-root") val bareRoot: Boolean?
)

data class SoilsAdaptation(
    @SerializedName("medium") val medium: Boolean?,
    @SerializedName("fine") val fine: Boolean?,
    @SerializedName("coarse") val coarse: Boolean?
)

enum class Binary {
    @SerializedName("Yes")
    YES,
    @SerializedName("No")
    NO
}

enum class Level {
    @SerializedName("None")
    NONE,
    @SerializedName("Low")
    LOW,
    @SerializedName("Medium")
    MEDIUM,
    @SerializedName("High")
    HIGH
}

enum class YearPeriod {
    @SerializedName("Spring")
    SPRING,
    @SerializedName("Summer")
    SUMMER,
    @SerializedName("Fall")
    FALL,
    @SerializedName("Winter")
    WINTER,
    @SerializedName("Year-round")
    YEAR_ROUND
}

enum class Rate {
    @SerializedName("None")
    NONE,
    @SerializedName("Slow")
    SLOW,
    @SerializedName("Moderate")
    MODERATE,
    @SerializedName("Rapid")
    RAPID
}

enum class Lifespan {
    @SerializedName("Short")
    SHORT,
    @SerializedName("Moderate")
    MODERATE,
    @SerializedName("Long")
    LONG
}

enum class Toxicity {
    @SerializedName("None")
    NONE,
    @SerializedName("Slight")
    SLIGHT,
    @SerializedName("Moderate")
    MODERATE,
    @SerializedName("Severe")
    SEVERE
}

enum class BloomPeriod {
    @SerializedName("Spring")
    SPRING,
    @SerializedName("Early Spring")
    EARLY_SPRING,
    @SerializedName("Mid Spring")
    MID_SPRING,
    @SerializedName("Late Spring")
    LATE_SPRING,
    @SerializedName("Summer")
    SUMMER,
    @SerializedName("Early Summer")
    EARLY_SUMMER,
    @SerializedName("Mid Summer")
    MID_SUMMER,
    @SerializedName("Late Summer")
    LATE_SUMMER,
    @SerializedName("Fall")
    FALL,
    @SerializedName("Winter")
    WINTER,
    @SerializedName("Late Winter")
    LATE_WINTER,
    @SerializedName("Indeterminate")
    INDETERMINATE
}

enum class GrowthPeriod {
    @SerializedName("Spring")
    SPRING,
    @SerializedName("Spring & Fall")
    SPRING_FALL,
    @SerializedName("Spring & Summer")
    SPRING_SUMMER,
    @SerializedName("Spring, Summer & Fall")
    SPRING_SUMMER_FALL,
    @SerializedName("Summer")
    SUMMER,
    @SerializedName("Summer & Fall")
    SUMMER_FALL,
    @SerializedName("Fall")
    FALL,
    @SerializedName("Fall, Winter & Spring")
    FALL_WINTER_SPRING,
    @SerializedName("Year-round")
    YEAR_ROUND
}