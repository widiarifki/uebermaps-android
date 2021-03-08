package id.widiarifki.uebermaps.presentation.main.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentAuthBinding
import id.widiarifki.uebermaps.presentation.main.MainFragment

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
            activity?.findNavController(R.id.rootNavHostFragment)?.navigate(R.id.action_mainFragment_to_loginFragment)
            //parentFragment?.findNavController()?.navigate(R.id.action_mainFragment_to_loginFragment)
            /*val a = parentFragment
            (parentFragment as? MainFragment)?.navigateToLogin()*/
        }
    }

}