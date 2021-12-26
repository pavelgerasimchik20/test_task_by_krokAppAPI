package com.geras.krok.ui

import androidx.lifecycle.*
import com.geras.krok.data.model.CityDto
import com.geras.krok.data.Repository
import com.geras.krok.domain.model.City
import com.geras.krok.ui.points.PointsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CityViewModel(private val repository: Repository) : ViewModel() {

    val cities: LiveData<List<City>> = repository.flowCityList.asLiveData()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateCitiesFromRemote()
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

}

class ViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CityViewModel(repository) as T
        }
        if (modelClass.isAssignableFrom(PointsViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PointsViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}
