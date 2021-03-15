package id.widiarifki.uebermaps.presentation.login

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentLoginBinding

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding>() {

    override val resourceLayout: Int = R.layout.fragment_login

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
    }

    private fun subscribeUI() {
        binding.apply {
            viewModel = loginViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeLogin()
    }

    private fun observeLogin() {
        loginViewModel.loginState.observe(viewLifecycleOwner) {
            if (!it.isLoading()) {
                if (it.isSuccess()) {
                    findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
                } else {
                    showToast(it.message)
                }
            }
        }
    }
}