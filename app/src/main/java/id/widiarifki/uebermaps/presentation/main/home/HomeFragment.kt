package id.widiarifki.uebermaps.presentation.main.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.databinding.FragmentHomeBinding
import id.widiarifki.uebermaps.helper.SpacedItemDecoration
import id.widiarifki.uebermaps.presentation.main.account.AccountViewModel
import id.widiarifki.uebermaps.presentation.main.home.adapters.HorizontalListAdapter

@AndroidEntryPoint
class HomeFragment: Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val homeViewModel: HomeViewModel by viewModels()
    //private val accountViewModel: AccountViewModel by viewModels()
    private val recommendedMapsAdapter = HorizontalListAdapter()
    private val latestMapsAdapter = HorizontalListAdapter()
    private val myMapsAdapter = HorizontalListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            viewModel = homeViewModel
            accountViewModel = accountViewModel
            lifecycleOwner = viewLifecycleOwner
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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeData()
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
            Log.v("userMaps", it.status_code.toString())
            if (it.isSuccess()) {
                myMapsAdapter.submitList(it.data)
            }
        }

        /*accountViewModel.userLogin.observe(viewLifecycleOwner) { userLoginState ->
            Log.v("userLoginState", userLoginState.status_code.toString())
            Log.v("userLoginState", (if (userLoginState.data != null) "visible" else "gone").toString())
            if (userLoginState.isSuccess() && userLoginState.data != null) {
                homeViewModel.myMaps.observe(viewLifecycleOwner) {
                    if (it.isSuccess()) {
                        myMapsAdapter.submitList(it.data)
                    }
                }
            }
        }*/
    }

}