package id.widiarifki.uebermaps.presentation.main.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.ui.fragment.LoadingDialogFragment
import id.widiarifki.uebermaps.databinding.FragmentAccountBinding

@AndroidEntryPoint
class AccountFragment : Fragment() {

    private lateinit var binding: FragmentAccountBinding
    private val accountViewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_account, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUIListener()
        subscribeUI()
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

    private fun showConfirmDialog(message: String?, confirmCallback: () -> Unit) {
        context?.let {
            val confirmDialog = AlertDialog.Builder(it)
                .setMessage(message)
                .setPositiveButton(R.string.dialog_action_yes) { _, _ -> confirmCallback() }
                .setNegativeButton(R.string.dialog_action_no) { _, _ -> }
                .create()

            confirmDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            confirmDialog.show()
        } ?: run {
            confirmCallback()
        }
    }

    private var dialogProgress: LoadingDialogFragment? = null

    private fun showLoadingDialog() {
        dialogProgress?.dismiss()

        dialogProgress = LoadingDialogFragment.newInstance(Bundle().apply {
            putBoolean(LoadingDialogFragment.PARAM_IS_CANCELLABLE, false)
            putString(LoadingDialogFragment.PARAM_MESSAGE, getString(R.string.msg_loading_logout))
        })
        dialogProgress?.show(childFragmentManager, LoadingDialogFragment.TAG)
    }

    private fun showToast(message: String, length: Int? = null) {
        Toast.makeText(context, message, length ?: Toast.LENGTH_SHORT).show()
    }
}