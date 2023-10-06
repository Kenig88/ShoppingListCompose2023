package com.kenig.shoppinglistcompose2023.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "shop_list_name")
data class ShoppingListItem(
    @PrimaryKey
    val id: Int? = null,
    val name: String,
    val time: String,
    val allItemsCount: Int, //это сколько всего элементов
    val allSelectedItemsCount: Int, //это сколько всего отмеченных элементов
)
