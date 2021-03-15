package id.widiarifki.uebermaps.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentMainBinding
import id.widiarifki.uebermaps.presentation.account.AccountViewModel

@AndroidEntryPoint
class NavbarFragment : BaseFragment<FragmentMainBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_main

    private var bottomNavController: NavController? = null
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigation()
    }

    private fun setupBottomNavigation() {
        val mainNavHostFragment = childFragmentManager.findFragmentById(R.id.bottomNavHostFragment) as NavHostFragment?
            ?: return
        bottomNavController = mainNavHostFragment.navController
        bottomNavController?.addOnDestinationChangedListener { controller, destination, arguments ->
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