package com.mvvm.mvvm.base

import android.content.pm.PackageManager
import android.os.Build
import android.view.Window
import android.view.WindowManager
import androidx.annotation.IdRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.mvvm.mvvm.R

abstract class BaseActivity : AppCompatActivity() {

//    override fun attachBaseContext(base: Context?) {
//        super.attachBaseContext(ViewPumpContextWrapper.wrap(base!!))
//    }

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    open fun setStatusBar() {
        val window: Window = this.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.colorPrimary)
        }
    }

    fun addFragment(fragment: Fragment, @IdRes frameId: Int, backstack: Boolean) {
        // AppConstants.checkNotNull(fragment);
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.add(frameId, fragment, fragment.javaClass.simpleName)
        if (backstack)
            fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceFragment(fragment: Fragment, @IdRes frameId: Int, backstack: Boolean) {
        //AppConstants.checkNotNull(fragment);
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frameId, fragment, fragment.javaClass.simpleName)
        if (backstack)
            fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun replaceNewFragment(fragment: Fragment, @IdRes frameId: Int, backstack: Boolean) {
        //AppConstants.checkNotNull(fragment);
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(frameId, fragment, fragment.javaClass.simpleName)
        if (backstack)
            fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commitAllowingStateLoss()
    }

//    fun openFragment(fragment: Fragment?) {
//        val transaction = supportFragmentManager.beginTransaction()
//        if (fragment != null) {
//            transaction.replace(R.id.frame, fragment)
//        }
//        transaction.addToBackStack(null)
//        transaction.commit()
//    }


    fun popFragment() {
        if (supportFragmentManager.backStackEntryCount > 1) {
            supportFragmentManager.popBackStack()
        } else {
            finish()
        }
    }

    fun hideFragment(fragment: Fragment) {
        //AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .hide(fragment)
            .commitAllowingStateLoss()
    }

    fun showFragment(fragment: Fragment) {
        //AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .show(fragment)
            .commitAllowingStateLoss()
    }

    fun removeFragment(fragment: Fragment) {
        // AppConstants.checkNotNull(fragment);
        supportFragmentManager.beginTransaction()
            .remove(fragment)
            .commitAllowingStateLoss()
    }

    protected fun backStackCount(): Int {
        //AppConstants.checkNotNull(fragment);
        return supportFragmentManager.backStackEntryCount
    }


    protected fun resetTitles() {
        try {
            val info = packageManager.getActivityInfo(componentName, PackageManager.GET_META_DATA)
            if (info.labelRes != 0) {
                setTitle(info.labelRes)
            }
        } catch (e: PackageManager.NameNotFoundException) {
            e.printStackTrace()
        }

    }
}