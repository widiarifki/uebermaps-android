package id.widiarifki.uebermaps.presentation.map.detail.comment

import android.os.Bundle
import androidx.fragment.app.Fragment
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.base.BaseFragment
import id.widiarifki.uebermaps.databinding.FragmentMapCommentBinding

class MapCommentFragment : BaseFragment<FragmentMapCommentBinding>() {

    override val resourceLayout: Int
        get() = R.layout.fragment_map_comment

    companion object {
        fun newInstance(args: Bundle?) : Fragment {
            val fragment = MapCommentFragment()
            fragment.arguments = args
            return fragment
        }
    }
}