package com.vholodynskyi.assignment.data.database.feature.contacts.source

import com.vholodynskyi.assignment.data.database.feature.contacts.dao.ContactsDao
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import kotlinx.coroutines.flow.Flow

class ContactLocalDataSourceImpl(
    private val dao: ContactsDao,
) : ContactLocalDataSource {

    override fun getAllContacts(): Flow<List<DbContact>> = dao.getContacts()

    override fun getAllContactsByFirstName(name: String): List<DbContact> =
        dao.getContactsByFirstName(name)

    override fun getContact(contactId: Long): DbContact = dao.getContact(contactId)

    override suspend fun insertAll(vararg dbContacts: DbContact): List<Long> {
        return dao.insertAll(*dbContacts)
    }

    override suspend fun insertContact(dbContact: DbContact) {
        dao.insert(dbContact)
    }

    override suspend fun insertContactList(dbContactList: List<DbContact>) {
        dao.insert(dbContactList)
    }

    override suspend fun updateContact(dbContact: DbContact) {
        dao.update(dbContact)
    }

    override suspend fun deleteContact(dbContact: DbContact) {
        dao.delete(dbContact)
    }

    override suspend fun clearDBWithFlagLogic() = dao.clearDBWithFlagLogic()

}