package com.geras.krok.ui.points

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.geras.krok.data.model.PointOfInterestDto
import com.geras.krok.data.Repository
import com.geras.krok.domain.model.PointOfInterest
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PointsViewModel(private val repository: Repository) : ViewModel() {

    val pointListDto: LiveData<List<PointOfInterest>> = repository.flowPointList.asLiveData()

    private val screenStateData = MutableStateFlow<State>(State.Progress)
    val screenState = screenStateData.asStateFlow()

    fun initWithId(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                repository.updateCityPointsById(id)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    sealed class State {

        object Progress : State()

        class Data(val data: List<PointOfInterest>) : State()
    }
}