package com.kseyko.satellite.data.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      15,November,2021      ║
╚════════════════════════════╝
 */
@Parcelize
data class SatelliteList(var id : Int, val active : Boolean, val name: String) : Parcelable
