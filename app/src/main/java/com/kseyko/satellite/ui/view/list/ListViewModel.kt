package com.kseyko.satellite.ui.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kseyko.satellite.data.models.SatelliteList
import com.kseyko.satellite.data.repository.SatelliteRepository
import com.kseyko.satellite.ui.adapter.SatelliteAdapter
import com.kseyko.satellite.ui.base.BaseViewModel
import com.kseyko.satellite.utils.Resource
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class ListViewModel(private val satelliteRepository: SatelliteRepository) : BaseViewModel() {

    var satelliteAdapter: SatelliteAdapter = SatelliteAdapter()

    private val _satelliteLiveData = MutableLiveData<Resource<List<SatelliteList>>>()
    var satelliteLiveData: LiveData<Resource<List<SatelliteList>>> = _satelliteLiveData

    fun fetchSatellite(text: String) {
        _satelliteLiveData.postValue(Resource.loading(null))
        viewModelScope.launch {
            val satelliteLive = satelliteRepository.searchSatellite(text)
            delay(200) // To show progress
            if (satelliteLive.isEmpty()) {
                _satelliteLiveData.postValue(Resource.error("No content in list", satelliteLive))
            } else {
                _satelliteLiveData.postValue(Resource.success(satelliteLive))
            }
        }
    }

    fun getAdapter(): SatelliteAdapter {
        return satelliteAdapter
    }

    fun setAdapterData(newSatelliteList: List<SatelliteList>) {
        satelliteAdapter.setSatelliteList(newSatelliteList)
    }


}