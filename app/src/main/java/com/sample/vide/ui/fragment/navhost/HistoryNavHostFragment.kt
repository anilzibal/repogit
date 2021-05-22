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
import com.sample.vide.databinding.FragmentNavhostHistoryBinding
import com.sample.vide.ui.fragment.BaseDataBindingFragment


/**
 * Navigation host fragment for the History tab.
 *
 * * [findNavController] returns the main navController, not the one required for navigating
 * nested [Fragment]s. That navController is retrieved from [NavHostFragment] of this fragment.
 *
 * * [DashboardNavHostFragment] graph uses [DashboardNavHostFragment] as start destination
 * so it should **navigate to next destination** to not get stuck.
 */
class HistoryNavHostFragment : BaseDataBindingFragment<FragmentNavhostHistoryBinding>() {
    override fun getLayoutRes(): Int = R.layout.fragment_navhost_history

    var navController: NavController? = null

    private val nestedNavHostFragmentId = R.id.nested_nav_host_fragment_dashboard
    private val navGraphId = R.navigation.nav_graph_dashboard

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val nestedNavHostFragment =
            childFragmentManager.findFragmentById(nestedNavHostFragmentId) as? NavHostFragment
        navController = nestedNavHostFragment?.navController

        if (navController!!.currentDestination == null || navController!!.currentDestination!!.id == navController!!.graph.startDestination) {
            navController?.navigate(R.id.history_dest)
        }

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
            if (navController?.currentDestination?.id == navController?.graph?.startDestination) {

                isEnabled = false
                requireActivity().onBackPressed()
                isEnabled = true
            } else if (isVisible) {
                navController?.navigateUp()
            }


        }
    }

}