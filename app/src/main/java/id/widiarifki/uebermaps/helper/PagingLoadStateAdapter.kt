package id.widiarifki.uebermaps.helper

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.LoadingStateLayoutBinding

class PagingLoadStateAdapter (
    private val retryCallback: () -> Unit
) : LoadStateAdapter<LoadStateViewHolder>() {

    override fun onBindViewHolder(holder: LoadStateViewHolder, loadState: LoadState) {
        val holderBinding = holder.binding

        with(loadState) {
            holderBinding.isLoading = this is LoadState.Loading
            (this as? LoadState.Error)?.error?.message?.let {
                holderBinding.message = it
            }
        }

        holderBinding.btnRetry.setOnClickListener {
            retryCallback.invoke()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): LoadStateViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.loading_state_layout, parent, false)
        return LoadStateViewHolder(LoadingStateLayoutBinding.bind(view))
    }
}

class LoadStateViewHolder(val binding: LoadingStateLayoutBinding) : RecyclerView.ViewHolder(binding.root)