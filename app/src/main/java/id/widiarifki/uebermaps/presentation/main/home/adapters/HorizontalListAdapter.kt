package id.widiarifki.uebermaps.presentation.main.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.databinding.ListItemMapsBinding

class HorizontalListAdapter : ListAdapter<Maps, HorizontalListItemHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HorizontalListItemHolder {
        val binding = ListItemMapsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HorizontalListItemHolder(binding)
    }

    override fun onBindViewHolder(holder: HorizontalListItemHolder, position: Int) {
        val data = getItem(position)
        holder.bind(data)
    }
}

class HorizontalListItemHolder(val binding: ListItemMapsBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Maps?) {
        data?.let {
            binding.maps = it
        }
    }
}

class DiffCallback : DiffUtil.ItemCallback<Maps>() {
    override fun areItemsTheSame(oldItem: Maps, newItem: Maps): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Maps, newItem: Maps): Boolean {
        return oldItem == newItem
    }

}