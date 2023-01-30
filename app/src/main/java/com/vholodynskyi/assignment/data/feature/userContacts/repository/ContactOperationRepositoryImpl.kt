package com.vholodynskyi.assignment.data.feature.userContacts.repository

import androidx.lifecycle.LiveData
import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.base.repository.BaseRepository
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.database.feature.contacts.source.ContactLocalDataSource
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import com.vholodynskyi.assignment.data.feature.userContacts.service.ContactsService
import com.vholodynskyi.assignment.di.GlobalFactory
import okhttp3.ResponseBody
import retrofit2.Response

class ContactOperationRepositoryImpl (
    private val dataSource: ContactLocalDataSource,
    private val service: ContactsService
) : BaseRepository(), ContactOperationRepository {

    override fun getAllContactsData():LiveData<List<DbContact>> = dataSource.getAllContacts()

    override fun getSelectedContactData(noteId: Long): DbContact = dataSource.getContact(noteId)

    override suspend fun insertContact(dbContact: DbContact) {
        dataSource.insertContact(dbContact)
    }

    override suspend fun updateContact(dbContact: DbContact) {
        dataSource.updateContact(dbContact)
    }

    override suspend fun deleteContact(dbContact: DbContact){
        dataSource.deleteContact(dbContact)
    }

    override suspend fun getContactsFromAPI(): DataWrapper<Response<ApiContactResponse>> {
        return safeApiCall {
            service.getContacts()
        }
    }

}