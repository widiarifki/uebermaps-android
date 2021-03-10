package id.widiarifki.uebermaps.presentation.main.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentAuthBinding
import id.widiarifki.uebermaps.presentation.RootActivity

class AuthFragment : Fragment() {

    private lateinit var binding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_auth, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.btnGotoLogin.setOnClickListener {
            (activity as? RootActivity)?.navigateRootHostTo(R.id.action_mainFragment_to_loginFragment)
        }
    }

}