package com.geras.krok.data.model

import com.google.gson.annotations.SerializedName

data class CityDto(

    @SerializedName("id")
    var id: Int? = null,
    @SerializedName("name")
    var name: String? = null,
    @SerializedName("lang")
    var lang: Int? = null,
    @SerializedName("logo")
    var logo: String? = null

)