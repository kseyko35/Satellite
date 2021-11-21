package com.kseyko.satellite.ui.view

import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kseyko.satellite.R
import com.kseyko.satellite.databinding.MainActivityBinding
import com.kseyko.satellite.ui.base.BaseActivity


class MainActivity : BaseActivity<MainActivityBinding>() {

    lateinit var navHostFragment: NavHostFragment

    override fun getViewBinding(): MainActivityBinding {
        return MainActivityBinding.inflate(layoutInflater)
    }

    override fun onInitView() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this, navController)
        this.supportActionBar?.hide()
    }

    override fun onInitListener() {

    }
}