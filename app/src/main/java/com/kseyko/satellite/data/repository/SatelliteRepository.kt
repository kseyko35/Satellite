package com.kseyko.satellite.data.repository

import com.kseyko.satellite.data.models.PositionDetail
import com.kseyko.satellite.data.models.Satellite
import com.kseyko.satellite.data.models.SatelliteList
import com.kseyko.satellite.data.remote.SatelliteApi


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      18,November,2021      ║
╚════════════════════════════╝
 */
class SatelliteRepository(private val satelliteApi: SatelliteApi) {

    suspend fun getSatellite(satellitePosition: Int): Satellite {
        return satelliteApi.getSatellite(satellitePosition)
    }

    fun getPosition(satellitePosition: Int): PositionDetail {
        return satelliteApi.getPosition(satellitePosition)
    }

    suspend fun searchSatellite(text: String): List<SatelliteList> {
        return satelliteApi.searchSatellite(text)
    }
}