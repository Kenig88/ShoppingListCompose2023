package com.kenig.shoppinglistcompose2023.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ShoppingListDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertItem(item: ShoppingListItem)

    @Delete
    suspend fun deleteItem(item: ShoppingListItem)

    @Query("SELECT * FROM shop_list_name")
    fun getAllItems(): Flow<List<ShoppingListItem>>
}