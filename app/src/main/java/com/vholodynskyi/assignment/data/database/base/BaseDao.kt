package com.vholodynskyi.assignment.data.database.base

import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Update

interface BaseDao<T> {

    /**
     * Insert an object in to database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(obj: T)

    /**
     * Insert an array of objects in to database.
     *
     * @param objects the objects to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(objects: List<T>)

    /**
     * Insert objects like a vararg parameters in to database.
     *
     * @param obj the object to be inserted.
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(vararg obj: T): List<Long>

    /**
     * Update an object from to database.
     *
     * @param obj the object to be updated
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(obj: T)

    /**
     * Delete an object from to database
     *
     * @param obj the object to be deleted
     */
    @Delete
    suspend fun delete(obj: T)
}