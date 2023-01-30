package com.vholodynskyi.assignment.data.database.base

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vholodynskyi.assignment.data.database.feature.contacts.dao.ContactsDao
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.database.utils.DatabaseConstant

@Database(
    entities = [DbContact::class],
    version = DatabaseConstant.ROOM_DATABASE_VERSION,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): ContactsDao
}