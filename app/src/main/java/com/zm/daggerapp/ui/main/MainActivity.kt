package com.zm.daggerapp.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.google.android.material.navigation.NavigationView
import com.zm.daggerapp.R
import com.zm.daggerapp.ui.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initialize()
    }

    private fun initialize() {
        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupActionBarWithNavController(this, navController, drawer_layout)
        NavigationUI.setupWithNavController(nav_view, navController)
        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> {
                sessionManager.logout()
            }
            android.R.id.home -> {
                return if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
                    drawer_layout.closeDrawer(GravityCompat.START)
                    true
                } else {
                    false
                }
            }
        }
        return true
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_posts -> {
                if(isValidDestination(R.id.postsScreen)){
                    Navigation.findNavController(this, R.id.nav_host_fragment).navigate(R.id.postsScreen)
                }
            }
            R.id.nav_profile ->{
                val navOptions = NavOptions.Builder()
                    .setPopUpTo(R.id.main, true)
                    .build()

                Navigation.findNavController(this, R.id.nav_host_fragment).navigate(
                    R.id.profileScreen,
                    null,
                    navOptions
                )
            }
        }
        item.isChecked = true
        drawer_layout.closeDrawer(GravityCompat.START)
        return false
    }

    private fun isValidDestination(destination: Int): Boolean {
        return destination != Navigation.findNavController(
            this,
            R.id.nav_host_fragment
        ).currentDestination!!.id
    }
    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(
            Navigation.findNavController(this, R.id.nav_host_fragment),
            drawer_layout
        )
    }
}
