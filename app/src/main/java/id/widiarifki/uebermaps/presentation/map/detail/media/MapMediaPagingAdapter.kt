package id.widiarifki.uebermaps.presentation.map.detail.media

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.data.model.Attachment
import id.widiarifki.uebermaps.databinding.ListItemMediaBinding

class MapMediaPagingAdapter : PagingDataAdapter<Attachment, MapMediaViewHolder>(DiffUtilCallback()) {

    override fun onBindViewHolder(holder: MapMediaViewHolder, position: Int) {
        val data = getItem(position)
        holder.binding.data = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MapMediaViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_media, parent, false)
        return MapMediaViewHolder(ListItemMediaBinding.bind(view))
    }
}

class MapMediaViewHolder(val binding: ListItemMediaBinding) : RecyclerView.ViewHolder(binding.root)

class DiffUtilCallback : DiffUtil.ItemCallback<Attachment>() {
    override fun areItemsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
        return oldItem == newItem
    }

}