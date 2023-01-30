package com.vholodynskyi.assignment.utils.extentions

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.util.Log
import android.view.View
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.commit
import com.vholodynskyi.assignment.utils.helper.isVersionHigherAndEqual
import com.vholodynskyi.assignment.utils.helper.isVersionInRange

fun FragmentActivity.navigateToTargetFragment(containerId: Int, targetFragment: Fragment){
    this.supportFragmentManager.commit {
        replace(containerId, targetFragment)
        addToBackStack(null)
    }
}

fun Activity.changeStatusBarVisibility(isVisible: Boolean, colorResInt: Int? = null) {
    Log.d("myTag","isVisible -> $isVisible")
    if (!isVisible) {
        this.window.apply {
            if (isVersionHigherAndEqual(Build.VERSION_CODES.R)) {
                WindowCompat.setDecorFitsSystemWindows(this, false)
            } else {
                clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            }
            statusBarColor = Color.TRANSPARENT
        }
    } else {
        this.window.apply {
            if (isVersionHigherAndEqual(Build.VERSION_CODES.R)) {
                WindowCompat.setDecorFitsSystemWindows(this, true)
            } else if (isVersionInRange(Build.VERSION_CODES.M..Build.VERSION_CODES.Q)) {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            } else {
                decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_VISIBLE
            }
            colorResInt?.let {
                statusBarColor = ContextCompat.getColor(context, colorResInt)
            }
        }
    }
}