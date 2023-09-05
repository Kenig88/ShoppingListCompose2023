package com.kenig.shoppinglistcompose2023.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface AddItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: AddItem)

    @Delete
    suspend fun deleteItem(item: AddItem)

    @Query("SELECT * FROM add_item WHERE listId = :listId")
    fun getAllItemsById(listId: Int): Flow<List<AddItem>>

    @Query("SELECT * FROM shop_list_name WHERE id = :listId")
    suspend fun getListItemById(listId: Int): ShoppingListItem

    @Update
    suspend fun insertItem(item: ShoppingListItem)
}