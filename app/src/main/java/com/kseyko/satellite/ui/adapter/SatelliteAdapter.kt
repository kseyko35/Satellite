package com.kseyko.satellite.ui.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.kseyko.satellite.R
import com.kseyko.satellite.data.model.SatelliteList
import com.kseyko.satellite.databinding.ItemSatelliteBinding
import com.kseyko.satellite.ui.base.toBinding


/**     Code with ❤
╔════════════════════════════╗
║   Created by Seyfi ERCAN   ║
╠════════════════════════════╣
║  seyfiercan35@hotmail.com  ║
╠════════════════════════════╣
║      16,November,2021      ║
╚════════════════════════════╝
 */
class SatelliteAdapter(private var satelliteList: List<SatelliteList> ) : RecyclerView.Adapter<SatelliteAdapter.Viewholder>() {



    class Viewholder(private val binding: ItemSatelliteBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(item : SatelliteList) {
            binding.textViewName.text = item.name
            binding.textViewActive.text = item.active.toString()
            if (item.active) binding.imageViewActive.setImageResource(R.mipmap.ic_green_dot)
            else binding.imageViewActive.setImageResource(R.mipmap.ic_red_dot)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder =
        Viewholder(parent.toBinding())


    override fun onBindViewHolder(holder: Viewholder, position: Int) = holder.bind(satelliteList[position])

    override fun getItemCount(): Int  =satelliteList.size
}