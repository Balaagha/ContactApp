package com.vholodynskyi.assignment.data.database.utils

import com.vholodynskyi.assignment.BuildConfig

object DatabaseConstant {
    // ROOM Database
    const val DATABASE_NAME = "app-database"
    const val CACHED_CONTACT_TABLE = "cached_contact_table"
    const val ROOM_DATABASE_VERSION = BuildConfig.VERSION_CODE
}