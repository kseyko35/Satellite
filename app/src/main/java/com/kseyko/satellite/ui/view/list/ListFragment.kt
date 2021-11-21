package com.kseyko.satellite.ui.view.list

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import com.kseyko.satellite.data.repository.ManualParsingImp
import com.kseyko.satellite.databinding.ListFragmentBinding
import com.kseyko.satellite.ui.base.BaseViewModelFragment
import com.kseyko.satellite.ui.view.factory.ViewModelFactory
import com.kseyko.satellite.utils.Status


class ListFragment : BaseViewModelFragment<ListFragmentBinding, ListViewModel>(),
    SearchView.OnQueryTextListener {

    override lateinit var viewModel: ListViewModel

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): ListFragmentBinding {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ManualParsingImp(requireContext()))
        )[ListViewModel::class.java]
        return ListFragmentBinding.inflate(inflater, container, false)
    }

    override fun onInitView() {
        binding.listViewModel = viewModel
        binding.lifecycleOwner = this
        binding.searchView.apply {
            isIconified = false
            clearFocus()
            setOnQueryTextFocusChangeListener { _, b ->
                if (!b) {
                    if (binding.searchView.query.toString().isEmpty()) {
                        binding.searchView.isIconified = true
                    }
                    binding.searchView.clearFocus()
                } else binding.searchView.isIconified = false
            }
        }
        binding.recyclerview.apply {
            setHasFixedSize(true)
            val itemDecoration = DividerItemDecoration(this.context, DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecoration)
        }
    }

    override fun onInitListener() {
        binding.searchView.setOnQueryTextListener(this)
    }

    override fun onObserverData() {
        super.onObserverData()
        viewModel.fetchSatellite("")
        viewModel.satelliteLiveData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                    it.data?.let { data ->
                        viewModel.setAdapterData(data)
                    }
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.ERROR -> {
                    hideLoading()
                    it.data?.let { data ->
                        viewModel.setAdapterData(data)
                    }
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return true
    }

    override fun onQueryTextChange(query: String?): Boolean {
        if (query != null) {
            viewModel.fetchSatellite(query)
        }
        return true
    }


}