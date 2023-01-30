package com.vholodynskyi.assignment.utils.helper

import android.os.Build

fun isVersionInRange(intRange: IntRange): Boolean {
    return Build.VERSION.SDK_INT in intRange
}

fun isVersionHigherAndEqual(version: Int): Boolean {
    return Build.VERSION.SDK_INT >= version
}

fun isVersionHigher(version: Int): Boolean {
    return Build.VERSION.SDK_INT > version
}

fun isVersionLowerAndEqual(version: Int): Boolean {
    return Build.VERSION.SDK_INT <= version
}

fun isVersionLower(version: Int): Boolean {
    return Build.VERSION.SDK_INT < version
} 