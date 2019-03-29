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
    @SerializedName("leaf_retention") val leafRetention: String?,
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

enum class Binary(val value: String) {
    @SerializedName("Yes")
    YES("Yes"),
    @SerializedName("No")
    NO("No")
}

enum class Level(val level: String) {
    @SerializedName("None")
    NONE("None"),
    @SerializedName("Low")
    LOW("Low"),
    @SerializedName("Medium")
    MEDIUM("Medium"),
    @SerializedName("High")
    HIGH("High")
}

enum class YearPeriod(val period: String) {
    @SerializedName("Spring")
    SPRING("Spring"),
    @SerializedName("Summer")
    SUMMER("Summer"),
    @SerializedName("Fall")
    FALL("Fall"),
    @SerializedName("Winter")
    WINTER("Winter"),
    @SerializedName("Year-round")
    YEAR_ROUND("Year-round")
}

enum class Rate(val rate: String) {
    @SerializedName("None")
    NONE("None"),
    @SerializedName("Slow")
    SLOW("Slow"),
    @SerializedName("Moderate")
    MODERATE("Moderate"),
    @SerializedName("Rapid")
    RAPID("Rapid")
}

enum class Lifespan(val lifespan: String) {
    @SerializedName("Short")
    SHORT("Short"),
    @SerializedName("Moderate")
    MODERATE("Moderate"),
    @SerializedName("Long")
    LONG("Long")
}

enum class Toxicity(val toxicity: String) {
    @SerializedName("None")
    NONE("None"),
    @SerializedName("Slight")
    SLIGHT("Slight"),
    @SerializedName("Moderate")
    MODERATE("Moderate"),
    @SerializedName("Severe")
    SEVERE("Severe")
}

enum class BloomPeriod(val period: String) {
    @SerializedName("Spring")
    SPRING("Spring"),
    @SerializedName("Early Spring")
    EARLY_SPRING("Early Spring"),
    @SerializedName("Mid Spring")
    MID_SPRING("Mid Spring"),
    @SerializedName("Late Spring")
    LATE_SPRING("Late Spring"),
    @SerializedName("Summer")
    SUMMER("Summer"),
    @SerializedName("Early Summer")
    EARLY_SUMMER("Early Summer"),
    @SerializedName("Mid Summer")
    MID_SUMMER("Mid Summer"),
    @SerializedName("Late Summer")
    LATE_SUMMER("Late Summer"),
    @SerializedName("Fall")
    FALL("Fall"),
    @SerializedName("Winter")
    WINTER("Winter"),
    @SerializedName("Late Winter")
    LATE_WINTER("Late Winter"),
    @SerializedName("Indeterminate")
    INDETERMINATE("Indeterminate")
}

enum class GrowthPeriod(val period: String) {
    @SerializedName("Spring")
    SPRING("Spring"),
    @SerializedName("Spring & Fall")
    SPRING_FALL("Spring & Fall"),
    @SerializedName("Spring & Summer")
    SPRING_SUMMER("Spring & Summer"),
    @SerializedName("Spring, Summer & Fall")
    SPRING_SUMMER_FALL("Spring, Summer & Fall"),
    @SerializedName("Summer")
    SUMMER("Summer"),
    @SerializedName("Summer & Fall")
    SUMMER_FALL("Summer & Fall"),
    @SerializedName("Fall")
    FALL("Fall"),
    @SerializedName("Fall, Winter & Spring")
    FALL_WINTER_SPRING("Fall, Winter & Spring"),
    @SerializedName("Year-round")
    YEAR_ROUND("Year-round")
}