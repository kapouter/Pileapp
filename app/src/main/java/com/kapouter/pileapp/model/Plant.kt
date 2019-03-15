package com.kapouter.pileapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "plants")
data class Plant(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("common_name") val name: String?,
    @SerializedName("scientific_name") val scientificName: String,
    @SerializedName("link") val link: String?
) {
}