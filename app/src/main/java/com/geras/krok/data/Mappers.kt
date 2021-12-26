package com.geras.krok.data

import com.geras.krok.data.model.CityDto
import com.geras.krok.data.model.PointOfInterestDto
import com.geras.krok.domain.model.City
import com.geras.krok.domain.model.PointOfInterest

fun PointOfInterestDto.toEntity(): PointOfInterest? {
    return PointOfInterest(

        id ?: return null,
        cityId ?: return null,
        language ?: return null,
        name ?: return null,
        text ?: return null,
        photo ?: return null,
        logo ?: return null
    )
}

fun CityDto.toEntity(): City? {
    return City(
        id ?: return null,
        name ?: return null,
        lang ?: return null,
        logo ?: return null
    )
}