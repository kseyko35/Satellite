package com.kseyko.satellite.ui.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kseyko.satellite.data.models.PositionDetail
import com.kseyko.satellite.data.models.Satellite
import com.kseyko.satellite.data.repository.SatelliteRepository
import com.kseyko.satellite.helper.Helper
import com.kseyko.satellite.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val satelliteRepository: SatelliteRepository) : BaseViewModel() {

    private val _satelliteLiveData = MutableLiveData<Satellite>()
    var satelliteLiveData: LiveData<Satellite> = _satelliteLiveData

    fun fetchSatellite(satellitePosition: Int) {
        viewModelScope.launch {
            val satelliteLive = withContext(Dispatchers.IO) {
                satelliteRepository.getSatellite(satellitePosition)
            }
            _satelliteLiveData.value = satelliteLive
            _satelliteLiveData.value!!.first_flight = Helper.formatDate(satelliteLive.first_flight)

        }
    }

    fun fetchPosition(satellitePosition: Int): PositionDetail {
        return satelliteRepository.getPosition(satellitePosition)
    }
}