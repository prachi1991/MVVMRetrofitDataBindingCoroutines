package com.mvvm.mvvm.home.view

import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.navigation.NavigationView
import com.mvvm.mvvm.R
import com.mvvm.mvvm.base.BaseActivity
import com.mvvm.mvvm.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.toolbar_main.view.*


class MainActivity : BaseActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Making notification bar transparent
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.viewModel = ViewModelProviders.of(this)[HomeViewModel::class.java]

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setStatusBar()
        }
        initViews()
        setToolbar()
    }

    private fun initViews() {
        binding.navView.setNavigationItemSelectedListener(this)
        if (backStackCount() == 0) {
            binding.navView.menu.getItem(0).isChecked = true
            val fragment = HomeFragment()
            replaceFragment(fragment, R.id.frame, true)

        }
    }

    private fun setToolbar() {
        binding.navView.itemIconTintList = null
        binding.toolbarMain.ivMenu.setOnClickListener {
            binding.drawerLayout.openDrawer(GravityCompat.START)
            if (binding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
                binding.drawerLayout.closeDrawer(GravityCompat.START)
            }
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val fragment = HomeFragment()
                replaceFragment(fragment, R.id.frame, false)
            }

        }
        binding.drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }


}

fun main(args: Array<String>) {
    var num1 = 3                                    //Line 1
    outer@ while (num1 > 0) {                       //Line 2
        var num2 = 3                                //Line 3
        inner@ while (num2 > 0) {                   //Line 4
            if (num1 == 2)                            //Line 5
            //Line 6
                inner@
                println("num1 = $num1, num2 = $num2")   //Line 7
            num2--
        }
        num1--
    }
}

class MyClass<out T>(val value: T)