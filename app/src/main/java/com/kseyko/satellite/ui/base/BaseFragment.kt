package com.kseyko.satellite.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
abstract class BaseFragment<VB : ViewBinding> : Fragment() {

    private var _binding: VB? = null

    val binding
        get() = _binding
            ?: throw IllegalStateException("Cannot access view in after view destroyed and before view creation")

    var viewId: Int = -1

    abstract fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean = false
    ): VB

    open fun onObserverData() {}

    abstract fun onInitView()

    abstract fun onInitListener()

    open fun clickHandling() {}

    open fun onPreInit(savedInstanceState: Bundle?) {}

    final override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = getViewBinding(inflater, container)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewId = binding.root.id
        onPreInit(savedInstanceState)
        onObserverData()
        onInitView()
        onInitListener()
        clickHandling()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun hideLoading() {
        (activity as BaseActivity<*>).hideLoading()
    }

    fun showLoading() {
        (activity as BaseActivity<*>).showLoading()
    }
}