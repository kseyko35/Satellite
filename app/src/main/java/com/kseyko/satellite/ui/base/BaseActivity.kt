package com.kseyko.satellite.ui.base

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.kseyko.satellite.widgets.LoadingDialog


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      16,November,2021      ║
╚════════════════════════════╝
 */
abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    private var loadingProgressBar : LoadingDialog? = null

    lateinit var binding : VB

    abstract fun getViewBinding(): VB

    open fun onObserverData(){}

    abstract fun onInitView()

    abstract fun onInitListener()

    open  fun onPreInit(savedInstanceState: Bundle?){}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = getViewBinding()
        setContentView(binding.root)
        onPreInit(savedInstanceState)
        onObserverData()
        onInitView()
        onInitListener()
    }

    fun hideLoading(){
        loadingProgressBar?.dismiss()
    }
    fun showLoading(){
        if (loadingProgressBar == null){
            loadingProgressBar = LoadingDialog(this)
        }
        loadingProgressBar?.show()
    }
}