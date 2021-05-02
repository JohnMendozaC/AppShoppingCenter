package com.lupesoft.appshoppingcenter.application

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil.setContentView
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        subscribeClickNavigation(binding)
    }

    private fun subscribeClickNavigation(binding: ActivityMainBinding) {
        (supportFragmentManager.findFragmentById(R.id.nav_host) as NavHostFragment).let { navHostFragment ->
            with(binding) {
                bottomNavigation.setOnNavigationItemSelectedListener { menuItem ->
                    when (menuItem.itemId) {
                        R.id.all_movies -> {
                            navHostFragment.findNavController()
                                .navigate(R.id.action_allMoviesFragment_self)
                        }
                        R.id.shopping_cart -> {
                            navHostFragment.findNavController()
                                .navigate(R.id.action_allMoviesFragment_to_shoppingCartFragment)
                        }
                    }
                    true
                }
            }
        }
    }
}