package com.vholodynskyi.assignment.data.database.feature.contacts.source

import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import kotlinx.coroutines.flow.Flow

interface ContactLocalDataSource {

    fun getAllContacts(): Flow<List<DbContact>>

    fun getAllContactsByFirstName(name: String): List<DbContact>

    fun getContact(contactId: Long): DbContact

    suspend fun insertAll(vararg dbContacts: DbContact): List<Long>

    suspend fun insertContact(dbContact: DbContact)

    suspend fun insertContactList(dbContactList: List<DbContact>)

    suspend fun updateContact(dbContact: DbContact)

    suspend fun deleteContact(dbContact: DbContact)

    suspend fun clearDBWithFlagLogic()

}