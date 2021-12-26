package com.geras.krok.data

import androidx.annotation.WorkerThread
import com.geras.krok.data.networking.TheCityApi
import com.geras.krok.data.networking.theCityApiService
import com.geras.krok.domain.model.City
import com.geras.krok.domain.model.PointOfInterest
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class Repository {

    private val _flowCityDtoList: MutableStateFlow<List<City>> =
        MutableStateFlow(emptyList())
    val flowCityList = _flowCityDtoList.asStateFlow()

    private val _flowPointList: MutableStateFlow<List<PointOfInterest>> =
        MutableStateFlow(emptyList())
    val flowPointList = _flowPointList.asStateFlow()

    private val service: TheCityApi = theCityApiService

    @WorkerThread
    suspend fun updateCitiesFromRemote() {
        val cities = service.getCities()
        _flowCityDtoList.value = cities.mapNotNull {
            it.toEntity()
        }.filter {
            it.name.isNotEmpty()
        }.filter {
            it.lang == 1
        }
    }

    @WorkerThread
    suspend fun updateCityPointsById(cityId: Int) {
        val points = service.getPoints(cityId)

        _flowPointList.value = points.asSequence().filter {
            it.cityId == cityId
        }.mapNotNull {
            it.toEntity()
        }.filter {
            it.name.isNotEmpty()
        }.filter {
            it.language == 1
        }.toList()
    }

}
