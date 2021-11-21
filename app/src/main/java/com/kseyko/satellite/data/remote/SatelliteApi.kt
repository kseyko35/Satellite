package com.kseyko.satellite.data.remote

import com.kseyko.satellite.data.models.PositionDetail
import com.kseyko.satellite.data.models.Satellite
import com.kseyko.satellite.data.models.SatelliteList


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      18,November,2021      ║
╚════════════════════════════╝
 */
interface SatelliteApi {

    suspend fun getSatellites(): List<SatelliteList>

    suspend fun getSatellite(satellitePosition: Int): Satellite

    fun getPosition(satellitePosition: Int): PositionDetail

    fun searchSatellite(text: String): List<SatelliteList>
}