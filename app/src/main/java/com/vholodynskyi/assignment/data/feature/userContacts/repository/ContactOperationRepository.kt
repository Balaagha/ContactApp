package com.vholodynskyi.assignment.data.feature.userContacts.repository

import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ContactOperationRepository {

    fun getAllContactsData(): Flow<List<DbContact>>

    fun getAllContactsByFirstNameData(name: String): List<DbContact>

    fun getSelectedContactData(noteId: Long): DbContact

    suspend fun insertContact(dbContact: DbContact)

    suspend fun insertContactList(dbContactList: List<DbContact>)

    suspend fun updateContact(dbContact: DbContact)

    suspend fun deleteContact(dbContact: DbContact)

    suspend fun clearDBWithFlagLogic()

    suspend fun getContactsFromAPI(): DataWrapper<Response<ApiContactResponse>>

}