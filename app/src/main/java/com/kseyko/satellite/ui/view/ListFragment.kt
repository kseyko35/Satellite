package com.kseyko.satellite.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.kseyko.satellite.data.model.SatelliteList
import com.kseyko.satellite.databinding.ListFragmentBinding
import com.kseyko.satellite.ui.adapter.SatelliteAdapter
import com.kseyko.satellite.ui.base.BaseViewModelFragment
import com.kseyko.satellite.ui.viewmodel.ListViewModel
import com.kseyko.satellite.utils.getJsonDataFromAsset
import org.json.JSONArray
import org.json.JSONObject
import com.google.gson.Gson

class ListFragment : BaseViewModelFragment<ListFragmentBinding, ListViewModel>() {

    override val viewModel: ListViewModel by viewModels()
    private var satelliteAdapter : SatelliteAdapter? = null
    private var satelliteList: List<SatelliteList> = ArrayList()

    override fun getViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): ListFragmentBinding {
        return ListFragmentBinding.inflate(inflater, container, false)
    }

    override fun onInitView() {
        val gson = Gson()
        val jsonFileString = getJsonDataFromAsset(requireContext(), "satellite-list.json")
        satelliteList = gson.fromJson(jsonFileString, Array<SatelliteList>::class.java).asList()
        satelliteAdapter = SatelliteAdapter(satelliteList)
        binding.recyclerview.adapter = satelliteAdapter
    }

    override fun onInitListener() {
    }

    override fun onDestroyView() {
        satelliteAdapter = null
        super.onDestroyView()
    }


}