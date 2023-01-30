package com.vholodynskyi.assignment.data.database.feature.contacts.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vholodynskyi.assignment.data.database.base.BaseDao
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact

@Dao
abstract class ContactsDao: BaseDao<DbContact> {
    @Transaction
    @Query("SELECT * FROM cached_contact_table" )
    abstract fun getContacts(): LiveData<List<DbContact>>

    @Transaction
    @Query("SELECT * FROM cached_contact_table WHERE id = :noteId " )
    abstract fun getContact(noteId: Long): DbContact

    @Query("DELETE FROM cached_contact_table WHERE id = (:contactId)")
    abstract suspend fun deleteById(contactId: Int)
}
