package com.vholodynskyi.assignment.data.feature.userContacts.repository

import androidx.lifecycle.LiveData
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import retrofit2.Response

interface ContactOperationRepository {

    fun getAllContactsData(): LiveData<List<DbContact>>

    fun getSelectedContactData(noteId: Long): DbContact

    suspend fun insertContact(dbContact: DbContact)

    suspend fun updateContact(dbContact: DbContact)

    suspend fun deleteContact(dbContact: DbContact)

    suspend fun getContactsFromAPI(): DataWrapper<Response<ApiContactResponse>>

}