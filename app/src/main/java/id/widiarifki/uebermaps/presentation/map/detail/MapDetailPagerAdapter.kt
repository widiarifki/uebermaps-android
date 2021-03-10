package id.widiarifki.uebermaps.presentation.map.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.widiarifki.uebermaps.R
import id.widiarifki.uebermaps.presentation.map.detail.comment.MapCommentFragment
import id.widiarifki.uebermaps.presentation.map.detail.media.MapMediaFragment
import id.widiarifki.uebermaps.presentation.map.detail.spots.MapSpotsFragment

class MapDetailPagerAdapter(fragment: Fragment, args: Bundle? = null) : FragmentStateAdapter(fragment) {

    private var mapOfFragments: LinkedHashMap<String, Fragment> = linkedMapOf(
        fragment.getString(R.string.label_spots) to MapSpotsFragment.newInstance(args),
        fragment.getString(R.string.label_media) to MapMediaFragment.newInstance(args),
        fragment.getString(R.string.label_comment) to MapCommentFragment.newInstance(args),
    )

    override fun getItemCount(): Int {
        return mapOfFragments.size
    }

    override fun createFragment(position: Int): Fragment {
        val arrayFragments = ArrayList<Fragment>(mapOfFragments.values)
        return arrayFragments[position]
    }

    fun getTitle(position: Int): String {
        val arrayTitles = ArrayList<String>(mapOfFragments.keys)
        return arrayTitles[position]
    }
}