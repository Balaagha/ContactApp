package com.vholodynskyi.assignment.utils.extentions

import androidx.fragment.app.Fragment


fun Fragment.changeStatusBarVisibility(isVisible: Boolean, colorResInt: Int? = null) {
    activity?.changeStatusBarVisibility(
        isVisible = isVisible,
        colorResInt = colorResInt
    )
}


fun Fragment.navigateToTargetFragment(containerId: Int, targetFragment: Fragment) {
    this.activity?.navigateToTargetFragment(
        containerId = containerId,
        targetFragment = targetFragment
    )
}