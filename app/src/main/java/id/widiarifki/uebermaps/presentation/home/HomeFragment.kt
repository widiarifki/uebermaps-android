package id.widiarifki.uebermaps.presentation.home

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.data.model.Maps
import id.widiarifki.uebermaps.databinding.FragmentHomeBinding
import id.widiarifki.uebermaps.helper.SpacedItemDecoration
import id.widiarifki.uebermaps.presentation.RootActivity

@AndroidEntryPoint
class HomeFragment: BaseFragment<FragmentHomeBinding>(), HorizontalListAdapter.ItemViewListener {

    override val resourceLayout: Int
        get() = R.layout.fragment_home

    private val homeViewModel: HomeViewModel by viewModels()
    private val recommendedMapsAdapter = HorizontalListAdapter(this)
    private val latestMapsAdapter = HorizontalListAdapter(this)
    private val myMapsAdapter = HorizontalListAdapter(this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeUI()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
    }

    private fun setupUI() {
        binding.apply {
            rvRecommendedMaps.apply {
                adapter = recommendedMapsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SpacedItemDecoration(context))
            }
            rvLatestMaps.apply {
                adapter = latestMapsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SpacedItemDecoration(context))
            }
            rvMyMaps.apply {
                adapter = myMapsAdapter
                layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                addItemDecoration(SpacedItemDecoration(context))
            }
        }
    }

    private fun subscribeUI() {
        binding.apply {
            viewModel = homeViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun observeData() {
        homeViewModel.recommendedMaps.observe(viewLifecycleOwner) {
            if (it.isSuccess()) {
                recommendedMapsAdapter.submitList(it.data)
            }
        }

        homeViewModel.latestMaps.observe(viewLifecycleOwner) {
            if (it.isSuccess()) {
                latestMapsAdapter.submitList(it.data)
            }
        }

        homeViewModel.myMaps.observe(viewLifecycleOwner) {
            if (it.isSuccess()) {
                myMapsAdapter.submitList(it.data)
            }
        }
    }

    override fun onClick(data: Maps?) {
        data?.id?.let {
            val args = Bundle()
            args.putInt("mapId", it)
            (activity as? RootActivity)?.navigateRootHostTo(R.id.action_mainFragment_to_mapDetailFragment, args)
        }
    }

}