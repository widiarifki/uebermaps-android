package id.widiarifki.uebermaps.presentation.notif;

import android.os.Bundle
import android.view.View
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentNotifBinding

class NotifFragment : BaseFragment<FragmentNotifBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_notif

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.text = "Notifications"
    }
}
