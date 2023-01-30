package com.vholodynskyi.assignment.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.view.inputmethod.InputMethodManager
import retrofit2.Response
import kotlin.math.roundToInt

fun <T>checkNetworkResultIsSuccess(data: Response<T>?): Boolean {
    return when(val code = data?.code()) {
        in 200 .. 299 -> {
            when (val resultBody = data?.body()) {
                null -> {
                    false
                }
                is List<*> -> {
                    (resultBody as List<*>).isNotEmpty()
                }
                else -> {
                    true
                }
            }
        }
        else -> {
            false
        }
    }
}

fun dpToPx(context: Context, dp: Int): Int {
    val displayMetrics = context.resources.displayMetrics
    return (dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT)).roundToInt()
}

fun pxToDp(px: Float, context: Context): Float {
    return (px / (context.resources.displayMetrics.densityDpi.toFloat() / DisplayMetrics.DENSITY_DEFAULT))
}

fun hideSoftKeyboard(activity: Activity) {
    val inputMethodManager = activity.getSystemService(
        Activity.INPUT_METHOD_SERVICE
    ) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(
        activity.currentFocus?.windowToken, 0
    )
}

