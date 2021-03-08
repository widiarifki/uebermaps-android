package id.widiarifki.uebermaps.presentation.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMainBinding
import id.widiarifki.uebermaps.helper.PreferenceConstant
import id.widiarifki.uebermaps.helper.PreferenceHelper
import id.widiarifki.uebermaps.presentation.main.account.AccountViewModel

@AndroidEntryPoint
class MainFragment : Fragment() {

    private lateinit var binding: FragmentMainBinding // TODO: Make it DRY
    private var bottomNavController: NavController? = null
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_main, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val mainNavHostFragment = childFragmentManager.findFragmentById(R.id.bottomNavHostFragment) as NavHostFragment?
            ?: return
        bottomNavController = mainNavHostFragment.navController
        bottomNavController?.addOnDestinationChangedListener { controller, destination, arguments ->
            // TODO: prosesnya blm cukup seamless & robust
            if (destination.id == R.id.accountFragment || destination.id == R.id.notifFragment) {
                authChecking()
            }
        }

        binding.bottomNavView.apply {
            bottomNavController?.let { setupWithNavController(it) }
        }
    }

    private fun authChecking() {
        accountViewModel.userLogin.observe(viewLifecycleOwner) {
            if (it.isSuccess() && it.data == null) {
                bottomNavController?.navigate(R.id.authFragment)
            }
        }
    }
}