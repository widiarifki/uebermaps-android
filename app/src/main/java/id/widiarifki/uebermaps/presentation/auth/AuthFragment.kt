package id.widiarifki.uebermaps.presentation.auth

import android.os.Bundle
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentAuthBinding
import id.widiarifki.uebermaps.presentation.RootActivity

class AuthFragment : BaseFragment<FragmentAuthBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_auth

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupUIListener()
    }

    private fun setupUIListener() {
        binding.btnGotoLogin.setOnClickListener {
            (activity as? RootActivity)?.navigateRootHostTo(R.id.action_mainFragment_to_loginFragment)
        }
    }

}