package id.widiarifki.uebermaps.presentation.map.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentMapDetailBinding

@AndroidEntryPoint
class MapDetailFragment : BaseFragment<FragmentMapDetailBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_map_detail

    private val mapDetalViewModel: MapDetailViewModel by viewModels()
    private val args: MapDetailFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        subscribeUI()
    }

    private fun setupUI() {
        binding.apply {
            // Setup toolbar
            toolbar.setNavigationOnClickListener {
                view?.findNavController()?.navigateUp()
            }

            // Setup viewpager
            val pagerAdapter = MapDetailPagerAdapter(this@MapDetailFragment, args.toBundle())
            viewPager.adapter = pagerAdapter
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                tab.text = pagerAdapter.getTitle(position)
            }.attach()
        }
    }

    private fun subscribeUI() {
        binding.apply {
            viewModel = mapDetalViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

}