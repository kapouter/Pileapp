package com.kapouter.pileapp.data

import com.google.gson.annotations.SerializedName

data class Plant(
    @SerializedName("id") val id: Int,
    @SerializedName("common_name") val name: String,
    @SerializedName("scientific_name") val scientificName: String,
    @SerializedName("link") val link: String
) {
}