package com.kseyko.satellite.utils

import androidx.recyclerview.widget.DiffUtil
import com.kseyko.satellite.data.models.SatelliteList


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      20,November,2021      ║
╚════════════════════════════╝
 */
class SatelliteDifUtil(
    private val oldSatellite: List<SatelliteList>,
    private val newSatellite: List<SatelliteList>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldSatellite.size
    }

    override fun getNewListSize(): Int {
        return newSatellite.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldSatellite[oldItemPosition].id == newSatellite[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return when {
            oldSatellite[oldItemPosition].id != newSatellite[newItemPosition].id -> false
            oldSatellite[oldItemPosition].name != newSatellite[newItemPosition].name -> false
            oldSatellite[oldItemPosition].active != newSatellite[newItemPosition].active -> false
            else -> true
        }
    }
}