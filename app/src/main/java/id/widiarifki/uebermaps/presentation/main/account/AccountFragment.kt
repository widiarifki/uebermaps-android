package id.widiarifki.uebermaps.presentation.main.account

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import id.widiarifki.uebermaps.R
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
            btnLogout.setOnClickListener { accountViewModel.actionLogout(it) }
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
}