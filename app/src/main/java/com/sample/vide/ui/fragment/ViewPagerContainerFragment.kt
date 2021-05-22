package com.sample.vide.ui.fragment

import android.os.Bundle
import android.view.View
import com.google.android.material.tabs.TabLayoutMediator
import com.sample.vide.R
import com.sample.vide.databinding.FragmentViewpagerContainerBinding
import com.sample.vide.ui.adapter.ChildFragmentStateAdapter

class ViewPagerContainerFragment : BaseDataBindingFragment<FragmentViewpagerContainerBinding>() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dataBinding?.let {
            it.viewPager?.adapter =
                ChildFragmentStateAdapter(
                    childFragmentManager,
                    viewLifecycleOwner.lifecycle
                )

            TabLayoutMediator(it.tabLayout, it.viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Video"
                    1 -> tab.text = "History"
                }
            }.attach()
        }

    }

    override fun onDestroyView() {

        val viewPager2 = dataBinding?.viewPager

        viewPager2?.let {
            it.adapter = null
        }

        super.onDestroyView()
    }

    override fun getLayoutRes(): Int = R.layout.fragment_viewpager_container


}
