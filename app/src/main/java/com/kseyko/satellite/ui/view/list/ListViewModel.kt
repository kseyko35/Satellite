package com.kseyko.satellite.ui.view.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.kseyko.satellite.data.models.SatelliteList
import com.kseyko.satellite.data.repository.SatelliteRepository
import com.kseyko.satellite.ui.adapter.SatelliteAdapter
import com.kseyko.satellite.ui.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListViewModel(private val satelliteRepository: SatelliteRepository) : BaseViewModel() {

    var satelliteAdapter: SatelliteAdapter = SatelliteAdapter()

    private val _satelliteLiveData = MutableLiveData<List<SatelliteList>>()
    var satelliteLiveData: MutableLiveData<List<SatelliteList>> = _satelliteLiveData

    private val _searchedSatelliteLiveData = MutableLiveData<List<SatelliteList>>()
    var searchedSatelliteLiveData: LiveData<List<SatelliteList>> = _searchedSatelliteLiveData

    fun fetchSatellite() {
        viewModelScope.launch {
            val satelliteLive = withContext(Dispatchers.IO) {
                satelliteRepository.getSatellites()
            }
            _satelliteLiveData.value = satelliteLive
        }
    }

    fun searchText(text: String) {
        viewModelScope.launch {
            val searchedSatelliteLive = withContext(Dispatchers.IO) {
                satelliteRepository.searchSatellite(text)
            }
            _searchedSatelliteLiveData.value = searchedSatelliteLive
        }
    }

    fun getAdapter(): SatelliteAdapter {
        return satelliteAdapter
    }

    fun setAdapterData(newSatelliteList: List<SatelliteList>) {
        satelliteAdapter.setSatelliteList(newSatelliteList)
    }


}