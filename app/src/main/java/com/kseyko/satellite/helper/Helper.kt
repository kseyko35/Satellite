package com.kseyko.satellite.helper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      21,November,2021      ║
╚════════════════════════════╝
 */
class Helper {
    @SuppressLint("SimpleDateFormat")
    companion object {
        fun formatDate(firstFlight: String?): String {
            var formatter = SimpleDateFormat("yyyy-MM-dd")
            val date = formatter.parse(firstFlight)
            formatter = SimpleDateFormat("dd.MM.yyyy")
            return formatter.format(date)
        }
    }
}