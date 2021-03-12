package id.widiarifki.uebermaps.presentation.map.detail.spots

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMapSpotsBinding
import id.widiarifki.uebermaps.helper.PagingLoadStateAdapter
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapSpotsFragment : Fragment() {

    private lateinit var binding: FragmentMapSpotsBinding
    private val mapSpotsViewModel: MapSpotsViewModel by viewModels()
    private val mapSpotsPagingAdapter = MapSpotsPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_spots, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun setupView() {
        binding.apply {
            rvSpots.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = mapSpotsPagingAdapter.withLoadStateHeaderAndFooter(
                    header = PagingLoadStateAdapter { mapSpotsPagingAdapter.retry() },
                    footer = PagingLoadStateAdapter { mapSpotsPagingAdapter.retry() }
                )
                addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL))
            }
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            mapSpotsViewModel.pagingMapSpots.collectLatest {
                mapSpotsPagingAdapter.submitData(it)
            }
        }

        lifecycleScope.launch {
            mapSpotsPagingAdapter.loadStateFlow.collectLatest { state ->
                with(state.refresh) {
                    binding.isLoading = this is LoadState.Loading && mapSpotsPagingAdapter.itemCount == 0
                    binding.isError = (this is LoadState.Error || this is LoadState.NotLoading) && mapSpotsPagingAdapter.itemCount == 0
                    (this as? LoadState.Error)?.error?.message?.let {
                        binding.message = it
                    } ?: run {
                        binding.message = getString(R.string.msg_empty_spot)
                    }
                    binding.retryCallback = View.OnClickListener { mapSpotsPagingAdapter.retry() }
                }
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?) : Fragment {
            val fragment = MapSpotsFragment()
            fragment.arguments = args
            return fragment
        }
    }

}