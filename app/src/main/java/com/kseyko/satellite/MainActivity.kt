package com.kseyko.satellite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.kseyko.satellite.databinding.MainActivityBinding
import com.kseyko.satellite.ui.base.BaseActivity
import com.kseyko.satellite.ui.view.ListFragment

class MainActivity : BaseActivity<MainActivityBinding>() {

    lateinit var navHostFragment: NavHostFragment

    override fun getViewBinding(): MainActivityBinding {
        return  MainActivityBinding.inflate(layoutInflater)
    }

    override fun onInitView() {
        navHostFragment = supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment
        val navController = navHostFragment.navController
        NavigationUI.setupActionBarWithNavController(this,navController)
    }

    override fun onInitListener() {

    }
}