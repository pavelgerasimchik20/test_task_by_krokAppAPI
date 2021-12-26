package com.geras.krok.domain.model

data class PointOfInterest(

    var id: Int,
    var cityId: Int,
    var language: Int,
    var name: String,
    var text: String,
    var photo: String,
    var logo: String
)