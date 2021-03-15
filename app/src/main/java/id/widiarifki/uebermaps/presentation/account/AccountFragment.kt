package id.widiarifki.uebermaps.presentation.account

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentAccountBinding

@AndroidEntryPoint
class AccountFragment : BaseFragment<FragmentAccountBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_account

    private val accountViewModel: AccountViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeUI()
        setupUIListener()
    }

    private fun setupUIListener() {
        binding.apply {
            btnEditProfile.setOnClickListener { onClickEditPassword() }
            btnEditPassword.setOnClickListener { onClickEditPassword() }
            btnLogout.setOnClickListener { onClickLogout() }
        }
    }

    private fun subscribeUI() {
        binding.apply {
            viewModel = accountViewModel
            lifecycleOwner = viewLifecycleOwner
        }
    }

    private fun onClickEditPassword() {
        Log.v("Action", "edit pass")
    }

    private fun onClickEditProfile() {
        Log.v("Action", "edit profile")
    }

    private fun onClickLogout() {
        showConfirmDialog(getString(R.string.msg_confirm_logout)) {
            accountViewModel.logoutUser().observe(viewLifecycleOwner) {
                when {
                    it.isLoading() -> showLoadingDialog()
                    it.isError() -> showToast(it.message)
                }
            }
        }
    }
}