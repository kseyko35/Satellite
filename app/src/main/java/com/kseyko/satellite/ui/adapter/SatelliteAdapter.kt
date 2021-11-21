package com.kseyko.satellite.ui.adapter

import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kseyko.satellite.data.models.SatelliteList
import com.kseyko.satellite.databinding.ItemSatelliteBinding
import com.kseyko.satellite.ui.base.toBinding
import com.kseyko.satellite.ui.view.list.ListFragmentDirections
import com.kseyko.satellite.utils.SatelliteDifUtil


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      16,November,2021      ║
╚════════════════════════════╝
 */
class SatelliteAdapter : RecyclerView.Adapter<SatelliteAdapter.SatelliteViewHolder>() {

    private var oldSatelliteList = mutableListOf<SatelliteList>()


    class SatelliteViewHolder(private val binding: ItemSatelliteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: SatelliteList) {
            binding.satelliteItem = item
            binding.executePendingBindings()

            binding.itemSatellite.setOnClickListener {
                val action = ListFragmentDirections.actionListFragmentToDetailFragment(
                    item.id,
                    item.name
                )
                Navigation.findNavController(it).navigate(action)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SatelliteViewHolder =
        SatelliteViewHolder(parent.toBinding())


    override fun onBindViewHolder(holder: SatelliteViewHolder, position: Int) =
        holder.bind(oldSatelliteList[position])

    override fun getItemCount(): Int = oldSatelliteList.size

    fun setSatelliteList(newSatelliteList: List<SatelliteList>) {
        val diffUtil = SatelliteDifUtil(oldSatelliteList, newSatelliteList)
        val difResults = DiffUtil.calculateDiff(diffUtil)
        oldSatelliteList = newSatelliteList.toMutableList()
        difResults.dispatchUpdatesTo(this)
    }

}