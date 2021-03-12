package id.widiarifki.uebermaps.presentation.map.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMapDetailBinding

@AndroidEntryPoint
class MapDetailFragment : Fragment() {

    private lateinit var binding: FragmentMapDetailBinding
    private val mapDetalViewModel: MapDetailViewModel by viewModels()
    private val args: MapDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        subscribeView()
    }

    private fun setupView() {
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

    private fun subscribeView() {
        binding.apply {
            viewModel = mapDetalViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

}