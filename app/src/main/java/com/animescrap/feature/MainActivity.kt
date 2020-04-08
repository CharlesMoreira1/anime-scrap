package com.animescrap.feature

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.animescrap.R
import com.animescrap.core.helper.setupWithNavController
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var currentNavController: LiveData<NavController>? = null
    private val navGraphIds = listOf(
        R.navigation.nav_home,
        R.navigation.nav_catalog
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar_main)
        initNavigation()
    }

    private fun initNavigation(){
        val controller = bottom_navigation.setupWithNavController(
            navGraphIds = navGraphIds,
            fragmentManager = supportFragmentManager,
            containerId = R.id.nav_host_fragment,
            intent = intent
        )

        controller.observe(this, Observer { navController ->
            setupActionBarWithNavController(navController)

            navController.addOnDestinationChangedListener { _, destination, _ ->
                when (destination.id) {
                    R.id.homeFragment -> showBottomNavigation()
                    R.id.catalogFragment -> showBottomNavigation()
                    else -> hideBottomNavigation()
                }
            }
        })

        currentNavController = controller
    }

    override fun onSupportNavigateUp(): Boolean = currentNavController?.value?.navigateUp() ?: false

    private fun showBottomNavigation(){
        bottom_navigation.visibility = View.VISIBLE
    }

    private fun hideBottomNavigation(){
        bottom_navigation.visibility = View.GONE
    }
}
