package com.lupesoft.appshoppingcenter.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).let { navHostFragment ->
            navHostFragment.navController
            findViewById<BottomNavigationView>(R.id.bottom_navigation)
                .setupWithNavController(navHostFragment.navController)
        }
    }
}