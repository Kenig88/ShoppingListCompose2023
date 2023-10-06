package com.kenig.shoppinglistcompose2023.data

import androidx.room.*

@Database(
    entities = [
        ShoppingListItem::class,
        AddItem::class,
        NoteItem::class,
        TestItem::class
    ],
    version = 2,
    exportSchema = true
)
abstract class MainDb : RoomDatabase() {

    abstract val shoppingListDao: ShoppingListDao
    abstract val noteDao: NoteDao
    abstract val addItemDao: AddItemDao
}