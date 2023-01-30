package com.vholodynskyi.assignment.data.database.feature.contacts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vholodynskyi.assignment.data.database.utils.DatabaseConstant.CACHED_CONTACT_TABLE
import java.io.Serializable

@Entity(tableName = CACHED_CONTACT_TABLE)
data class DbContact(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo val firstName: String?,
    @ColumnInfo val lastName: String?,
    @ColumnInfo val email: String?,
    @ColumnInfo val photo: String?,
    @ColumnInfo val isDeleted: Boolean? = false
) : Serializable
