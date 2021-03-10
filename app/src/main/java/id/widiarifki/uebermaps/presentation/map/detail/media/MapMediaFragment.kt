package id.widiarifki.uebermaps.presentation.map.detail.media

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMapMediaBinding
import id.widiarifki.uebermaps.helper.PagingLoadStateAdapter
import id.widiarifki.uebermaps.helper.SpacedItemDecoration
import id.widiarifki.uebermaps.presentation.map.detail.spots.MapSpotsViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MapMediaFragment : Fragment() {

    private lateinit var binding: FragmentMapMediaBinding
    private val mapMediaViewModel: MapMediaViewModel by viewModels()
    private val mediaPagingAdapter = MapMediaPagingAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_media, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rvMedia.apply {
            layoutManager = GridLayoutManager(context, 3)
            adapter = mediaPagingAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter{ mediaPagingAdapter.retry()},
                footer = PagingLoadStateAdapter{ mediaPagingAdapter.retry()}
            )
            addItemDecoration(SpacedItemDecoration(context))
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            mapMediaViewModel.pagingMapMedia.collectLatest {
                mediaPagingAdapter.submitData(it)
            }
        }
    }

    companion object {
        fun newInstance(args: Bundle?) : Fragment {
            val fragment = MapMediaFragment()
            fragment.arguments = args
            return fragment
        }
    }
}