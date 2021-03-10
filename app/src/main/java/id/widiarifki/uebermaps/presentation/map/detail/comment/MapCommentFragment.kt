package id.widiarifki.uebermaps.presentation.map.detail.comment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.databinding.FragmentMapCommentBinding

class MapCommentFragment : Fragment() {

    private lateinit var binding: FragmentMapCommentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_map_comment, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        fun newInstance(args: Bundle?) : Fragment {
            val fragment = MapCommentFragment()
            fragment.arguments = args
            return fragment
        }
    }
}