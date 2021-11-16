package com.kseyko.satellite.ui.base

import android.os.Bundle
import androidx.viewbinding.ViewBinding


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      16,November,2021      ║
╚════════════════════════════╝
 */
abstract class BaseViewModelFragment<VB : ViewBinding, VM : BaseViewModel> : BaseFragment<VB>() {
    abstract val viewModel: VM

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onPreInit(savedInstanceState: Bundle?) {
        super.onPreInit(savedInstanceState)
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onDestroy() {
        super.onDestroy()
    }
}