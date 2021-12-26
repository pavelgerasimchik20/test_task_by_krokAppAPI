package com.geras.krok.data.networking

import com.geras.krok.data.model.CityDto
import com.geras.krok.data.model.PointOfInterestDto
import retrofit2.http.GET
import retrofit2.http.Path

interface TheCityApi {

    @GET("api/get_cities/11/")
    suspend fun getCities(): List<CityDto>

    @GET("api/get_points/{id}")
    suspend fun getPoints(@Path("id") id: Int): List<PointOfInterestDto>
}

