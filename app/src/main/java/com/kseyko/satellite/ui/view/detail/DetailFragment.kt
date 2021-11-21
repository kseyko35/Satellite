package com.kseyko.satellite.ui.view.detail

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.kseyko.satellite.data.models.PositionDetail
import com.kseyko.satellite.data.repository.ManualParsingImp
import com.kseyko.satellite.databinding.DetailFragmentBinding
import com.kseyko.satellite.ui.base.BaseViewModelFragment
import com.kseyko.satellite.ui.view.factory.ViewModelFactory
import com.kseyko.satellite.utils.Status

class DetailFragment : BaseViewModelFragment<DetailFragmentBinding, DetailViewModel>() {

    override lateinit var viewModel: DetailViewModel

    private val args by navArgs<DetailFragmentArgs>()

    private lateinit var satelliteItemPosition: PositionDetail

    lateinit var satelliteName: String
    private var satellitePosition: Int = 0
    private var counter: Int = 0
    lateinit var handler: Handler

    override fun getDataBinding(
        inflater: LayoutInflater,
        container: ViewGroup?,
        attachToParent: Boolean
    ): DetailFragmentBinding {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(ManualParsingImp(requireContext()))
        )[DetailViewModel::class.java]
        return DetailFragmentBinding.inflate(inflater, container, false)
    }

    override fun onInitView() {
        binding.detailViewModel = viewModel
        binding.detailFragment = this
        binding.lifecycleOwner = this

        handler = Handler(Looper.getMainLooper())
        satelliteName = args.name
        satellitePosition = args.id - 1

    }

    override fun onObserverData() {
        super.onObserverData()
        satelliteItemPosition = viewModel.fetchPosition(satellitePosition)
        viewModel.fetchSatellite(satellitePosition)
        viewModel.satelliteLiveData.observe(this, {
            when (it.status) {
                Status.SUCCESS -> {
                    hideLoading()
                }
                Status.LOADING -> {
                    showLoading()
                }
                Status.ERROR -> {
                    hideLoading()
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(updatePosition)
    }

    override fun onResume() {
        super.onResume()
        handler.post(updatePosition)
    }

    private val updatePosition = object : Runnable {
        override fun run() {
            binding.textViewDetailLastPositionSol.text =
                "(${satelliteItemPosition.positions[counter].posX},${satelliteItemPosition.positions[counter].posY})"
            if (counter < satelliteItemPosition.positions.size - 1) counter++
            else counter = 0
            handler.postDelayed(this, 3000)
        }
    }

    override fun onInitListener() {
    }
}