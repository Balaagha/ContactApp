package com.vholodynskyi.assignment.data.database.feature.contacts.source

import androidx.lifecycle.LiveData
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact

interface ContactLocalDataSource {

    fun getAllContacts(): LiveData<List<DbContact>>

    fun getContact(noteId: Long): DbContact

    suspend fun insertAll(vararg dbContacts: DbContact): List<Long>

    suspend fun insertContact(dbContact: DbContact)

    suspend fun insertContactList(dbContactList: List<DbContact>)

    suspend fun updateContact(dbContact: DbContact)

    suspend fun deleteContact(dbContact: DbContact)

}