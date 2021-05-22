package com.sample.vide.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.sample.vide.ui.fragment.navhost.HistoryNavHostFragment
import com.sample.vide.ui.fragment.navhost.VideoNavHostFragment

/**
 * FragmentStateAdapter to contain ViewPager2 fragments inside another fragment.
 *
 * * Create FragmentStateAdapter with viewLifeCycleOwner instead of Fragment to make sure
 * that it lives between [Fragment.onCreateView] and [Fragment.onDestroyView] while [View] is alive
 *
 */
class ChildFragmentStateAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> VideoNavHostFragment()
            1 -> HistoryNavHostFragment()
            else -> VideoNavHostFragment()
        }
    }

}