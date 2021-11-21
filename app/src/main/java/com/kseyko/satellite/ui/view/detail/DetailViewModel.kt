package com.kseyko.satellite.ui.view.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kseyko.satellite.data.models.PositionDetail
import com.kseyko.satellite.data.models.Satellite
import com.kseyko.satellite.data.repository.SatelliteRepository
import com.kseyko.satellite.ui.base.BaseViewModel
import com.kseyko.satellite.utils.Resource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel(private val satelliteRepository: SatelliteRepository) : BaseViewModel() {

    private val _satelliteLiveData = MutableLiveData<Resource<Satellite>>()
    var satelliteLiveData: LiveData<Resource<Satellite>> = _satelliteLiveData

    fun fetchSatellite(satellitePosition: Int) {
        _satelliteLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val satelliteLive = withContext(Dispatchers.IO) {
                satelliteRepository.getSatellite(satellitePosition)
            }
            delay(200)
            _satelliteLiveData.postValue(Resource.success(satelliteLive))
        }
    }

    fun fetchPosition(satellitePosition: Int): PositionDetail {
        return satelliteRepository.getPosition(satellitePosition)
    }
}