package com.sample.vide.ui.fragment.navhost

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.sample.vide.R
import com.sample.vide.databinding.FragmentNavhostVideoBinding
import com.sample.vide.ui.fragment.BaseDataBindingFragment
import com.sample.vide.ui.fragment.VideoListFragment


/**
 * Navigation host fragment for the Video tab.
 *
 * * [findNavController] returns the main navController, not the one required for navigating
 * nested [Fragment]s. That navController is retrieved from [NavHostFragment] of this fragment.
 *
 * * Navigation graph of this fragment uses [VideoListFragment] so it navigates to it and
 * back stack entry count is one when  [VideoListFragment] is displayed
 */
class VideoNavHostFragment : BaseDataBindingFragment<FragmentNavhostVideoBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_video

    var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_home
    private val navGraphId = R.navigation.nav_graph_video

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*
            🔥 This is navController we get from findNavController not the one required
            for navigating nested fragments
         */
        val mainNavController =
            Navigation.findNavController(requireActivity(), R.id.nav_host_fragment)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        // Listen on back press
        listenOnBackPressed()

    }


    private fun listenOnBackPressed() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, callback)
    }

    override fun onResume() {
        super.onResume()
        callback.isEnabled = true
    }

    override fun onPause() {
        super.onPause()
        callback.isEnabled = false
    }

    /**
     * This callback should be created with Disabled because on rotation ViewPager creates
     * NavHost fragments that are not on screen, destroys them afterwards but it might take
     * up to 5 seconds.
     *
     * ### Note: During that interval touching back button sometimes call incorrect [OnBackPressedCallback.handleOnBackPressed] instead of this one if callback is **ENABLED**
     */
    val callback = object : OnBackPressedCallback(false) {

        override fun handleOnBackPressed() {

            // Check if it's the root of nested fragments in this navhost
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true
            } else {
                navController?.navigateUp()
            }


        }
    }

}
