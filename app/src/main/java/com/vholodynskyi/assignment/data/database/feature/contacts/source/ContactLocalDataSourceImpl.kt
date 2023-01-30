package com.vholodynskyi.assignment.data.database.feature.contacts.source

import androidx.lifecycle.LiveData
import com.vholodynskyi.assignment.data.database.feature.contacts.dao.ContactsDao
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact

class ContactLocalDataSourceImpl (
    private val dao: ContactsDao,
) : ContactLocalDataSource {

    override fun getAllContacts():LiveData<List<DbContact>> = dao.getContacts()

    override fun getContact(noteId: Long): DbContact = dao.getContact(noteId)

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

    override suspend fun deleteContact(dbContact: DbContact){
        dao.delete(dbContact)
    }

}