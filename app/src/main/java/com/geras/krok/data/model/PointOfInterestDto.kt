package com.geras.krok.data.model

import com.google.gson.annotations.SerializedName

data class PointOfInterestDto(

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("city_id")
    var cityId: Int? = null,
    @SerializedName("lang")
    var language: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("text")
    var text: String? = null,
    @SerializedName("photo")
    var photo: String? = null,
    @SerializedName("logo")
    var logo: String? = null
)