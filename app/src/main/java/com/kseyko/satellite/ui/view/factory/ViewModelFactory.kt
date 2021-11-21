package com.kseyko.satellite.ui.view.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kseyko.satellite.data.repository.ManualParsingImp
import com.kseyko.satellite.data.repository.SatelliteRepository
import com.kseyko.satellite.ui.view.detail.DetailViewModel
import com.kseyko.satellite.ui.view.list.ListViewModel


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      21,November,2021      ║
╚════════════════════════════╝
 */
class ViewModelFactory(private val api: ManualParsingImp) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailViewModel::class.java)) {
            return DetailViewModel(SatelliteRepository(api)) as T
        } else if (modelClass.isAssignableFrom(ListViewModel::class.java)) {
            return ListViewModel(SatelliteRepository(api)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}