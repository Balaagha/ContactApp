package com.vholodynskyi.assignment.data.database.feature.contacts.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.vholodynskyi.assignment.data.database.base.BaseDao
import com.vholodynskyi.assignment.data.database.feature.contacts.model.DbContact
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ContactsDao: BaseDao<DbContact> {
    @Transaction
    @Query("SELECT * FROM cached_contact_table ORDER BY firstName ASC" )
    abstract fun getContacts(): Flow<List<DbContact>>

    @Transaction
    @Query("SELECT * FROM cached_contact_table WHERE firstName = :name" )
    abstract fun getContactsByFirstName(name: String): List<DbContact>

    @Transaction
    @Query("SELECT * FROM cached_contact_table WHERE id = :contactId " )
    abstract fun getContact(contactId: Long): DbContact

    @Query("DELETE FROM cached_contact_table WHERE id = (:contactId)")
    abstract suspend fun deleteById(contactId: Int)

    @Query("DELETE FROM cached_contact_table WHERE isDeleted = 0")
    abstract suspend fun clearDBWithFlagLogic()
}
