package id.widiarifki.uebermaps.presentation.main.notif;

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.ui.fragment.BindingFragment
import id.widiarifki.uebermaps.databinding.FragmentHomeBinding
import id.widiarifki.uebermaps.databinding.FragmentNotifBinding
import id.widiarifki.uebermaps.presentation.main.home.HomeViewModel

class NotifFragment : Fragment() {

    private lateinit var binding: FragmentNotifBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_notif, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.text = "Notif"
    }
}
