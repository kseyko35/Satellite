package com.kseyko.satellite.data.repository

import android.content.Context
import com.google.gson.Gson
import com.kseyko.satellite.data.models.*
import com.kseyko.satellite.data.remote.SatelliteApi
import com.kseyko.satellite.utils.getJsonDataFromAsset
import java.util.*


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      18,November,2021      ║
╚════════════════════════════╝
 */
class ManualParsingImp(private val context: Context) : SatelliteApi {

    override suspend fun getSatellite(satellitePosition: Int): Satellite {
        val jsonFileString = getJsonDataFromAsset(context, "satellite-detail.json")
        return Gson().fromJson(jsonFileString, Array<Satellite>::class.java)
            .asList()[satellitePosition]
    }

    override fun getPosition(satellitePosition: Int): PositionDetail {
        val jsonFileStringPosition = getJsonDataFromAsset(context, "positions.json")
        return Gson().fromJson(
            jsonFileStringPosition,
            PositionList::class.java
        ).list[satellitePosition]
    }

    override suspend fun searchSatellite(text: String): List<SatelliteList> {
        val jsonFileString = getJsonDataFromAsset(context, "satellite-list.json")
        val filteredSatellite = Gson().fromJson(jsonFileString, Array<SatelliteList>::class.java)
        return filteredSatellite.filter {
            it.name.lowercase(Locale.getDefault())
                .contains(text.lowercase(Locale.getDefault()))
        }
    }
}