package com.vholodynskyi.assignment.utils.extentions

import android.os.Bundle

fun <T> Bundle.put(key: String, value: T) {
    /**
     * For add class is like this ClassName -> putParcelable(key, value)

     */
    when (value) {
        is Boolean -> putBoolean(key, value)
        is String -> putString(key, value)
        is Int -> putInt(key, value)
        is Short -> putShort(key, value)
        is Long -> putLong(key, value)
        is Byte -> putByte(key, value)
        is ByteArray -> putByteArray(key, value)
        is Char -> putChar(key, value)
        is CharArray -> putCharArray(key, value)
        is CharSequence -> putCharSequence(key, value)
        is Float -> putFloat(key, value)
        else -> throw IllegalStateException("Type of property $key is not supported")
    }
}