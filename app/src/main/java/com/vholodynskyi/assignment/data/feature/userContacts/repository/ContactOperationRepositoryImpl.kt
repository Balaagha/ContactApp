package com.vholodynskyi.assignment.data.feature.userContacts.repository

import com.vholodynskyi.assignment.data.base.models.DataWrapper
import com.vholodynskyi.assignment.data.base.repository.BaseRepository
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import com.vholodynskyi.assignment.data.database.feature.contacts.source.ContactLocalDataSource
import com.vholodynskyi.assignment.data.feature.userContacts.models.ApiContactResponse
import com.vholodynskyi.assignment.data.feature.userContacts.service.ContactsService
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

class ContactOperationRepositoryImpl(
    private val dataSource: ContactLocalDataSource,
    private val service: ContactsService
) : BaseRepository(), ContactOperationRepository {

    override fun getAllContactsData(): Flow<List<DbContact>> = dataSource.getAllContacts()

    override fun getAllContactsByFirstNameData(name: String): List<DbContact> =
        dataSource.getAllContactsByFirstName(name)

    override fun getSelectedContactData(contactId: Long): DbContact = dataSource.getContact(contactId)

    override suspend fun insertContact(dbContact: DbContact) = dataSource.insertContact(dbContact)

    override suspend fun insertContactList(dbContactList: List<DbContact>) = dataSource.insertContactList(dbContactList)

    override suspend fun updateContact(dbContact: DbContact) = dataSource.updateContact(dbContact)

    override suspend fun deleteContact(dbContact: DbContact) = dataSource.deleteContact(dbContact)

    override suspend fun deleteContact(id: Int) = dataSource.deleteContact(id)

    override suspend fun getContactsFromAPI(): DataWrapper<Response<ApiContactResponse>> =
        safeApiCall {
            service.getContacts()
        }

    override suspend fun clearDBWithFlagLogic() = dataSource.clearDBWithFlagLogic()

}