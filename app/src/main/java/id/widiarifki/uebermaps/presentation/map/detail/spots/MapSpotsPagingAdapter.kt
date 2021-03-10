package id.widiarifki.uebermaps.presentation.map.detail.spots

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.data.model.Spot
import id.widiarifki.uebermaps.databinding.ListItemSpotBinding

class MapSpotsPagingAdapter : PagingDataAdapter<Spot, MapSpotsViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MapSpotsViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapSpotsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_spot, parent, false)
        return MapSpotsViewHolder(ListItemSpotBinding.bind(view))
    }
}

class MapSpotsViewHolder(val binding: ListItemSpotBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Spot>() {
    override fun areItemsTheSame(oldItem: Spot, newItem: Spot): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Spot, newItem: Spot): Boolean {
        return oldItem == newItem
    }

}