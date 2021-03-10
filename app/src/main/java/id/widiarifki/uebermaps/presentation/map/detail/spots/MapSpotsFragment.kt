package id.widiarifki.uebermaps.presentation.map.detail.spots

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMapSpotsBinding
import id.widiarifki.uebermaps.helper.PagingLoadStateAdapter
import id.widiarifki.uebermaps.helper.SpacedItemDecoration
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
        setupRecyclerView()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun setupRecyclerView() {
        binding.rvSpots.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = mapSpotsPagingAdapter.withLoadStateHeaderAndFooter(
                header = PagingLoadStateAdapter{ mapSpotsPagingAdapter.retry()},
                footer = PagingLoadStateAdapter{ mapSpotsPagingAdapter.retry()}
            )
            addItemDecoration(SpacedItemDecoration(context))
        }
    }

    private fun observeData() {
        lifecycleScope.launch {
            mapSpotsViewModel.pagingMapSpots.onEach {
                Log.v("CURRENTPAGE-onEach", it.toString())
            }.onCompletion {
                Log.v("CURRENTPAGE-onComplet", "1")
            }.onEmpty {
                Log.v("CURRENTPAGE-onEmpty", "1")
            }.onStart {
                Log.v("CURRENTPAGE-onStart", "1")
            }.catch {
                Log.v("CURRENTPAGE-catch", "1")
            }.collectLatest {
                mapSpotsPagingAdapter.submitData(it)
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