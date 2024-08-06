package com.example.inventory.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) to perform CRUD operations on the SQLite database.
 *
 * For annotated methods in this interface; [Insert], [Update] & [Delete],
 * Room has the default implementations for each of this methods for you.
 * Room will implement this interface to use the methods defined here.
 */
@Dao
interface ItemDao {
    /**
     * Coroutine function to insert a new item in the table "items".
     *
     * Room will handle the details for this simple insert query.
     * It wil add the new [item] entity row in the table.
     * If the same item tries to be updated at the same time, ignore the new data
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: Item)

    /** Coroutine to update an item entity in "items" table */
    @Update
    suspend fun update(item: Item)

    /** Coroutine to delete an item entity from "items" table */
    @Delete
    suspend fun delete(item: Item)

    /** SQLite query to get a particular item in the "items" table using its id (primary key)
     *
     * Returning the Item from the flow makes sure you fetch the latest Item even when the database data updates
     */
    @Query("SELECT * FROM items WHERE id = :id")
    fun getItem(id: Int): Flow<Item>

    /** SQLite query to get all items in the "items" table ordered by name in ascending order */
    @Query("SELECT * FROM items ORDER BY name ASC")
    fun getAllItems(): Flow<List<Item>>
}