package com.sample.vide.ui.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.viewpager2.widget.ViewPager2
import com.sample.vide.R
import com.sample.vide.ui.fragment.navhost.VideoNavHostFragment


/**
 * Example that uses [ViewPager2] with tabs and each page with it's own back stack
 * and navigation.
 *
 * * [NavHostFragment] fragment such as [VideoNavHostFragment] create their child fragments
 * and can navigate in them
 *
 * * [VideoNavHostFragment] uses graph with fragment that is displayed
 * * [DashboardNavHostFragment] uses graph with itself as start destination so it should
 * check for the [NavController.getCurrentDestination] to navigate to it when device rotated
 *
 * * [LoginFragment1] is added to main graph, because of that appbar back navigation only works
 * with the [ViewPagerContainerFragment]'s [NavController]
 *
 */
class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Create Toolbar and set support action bar
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Get NavController
        val navController = findNavController(R.id.nav_host_fragment)

        // Get App Configuration from nav graph
        appBarConfiguration = AppBarConfiguration(navController.graph)

        // Handles arrow back button
        setupActionBarWithNavController(navController, appBarConfiguration)


        initToolbar()

    }

    private fun initToolbar() {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title ="ITunes"
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
    }


    // This function is required with appbar to handle back button
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


}