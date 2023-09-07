package com.kenig.shoppinglistcompose2023.data

import kotlinx.coroutines.flow.Flow

class NoteItemRepoImpl(private val dao: NoteDao) : NoteRepository {
    override suspend fun insertItem(item: NoteItem) {
        dao.insertItem(item)
    }

    override suspend fun deleteItem(item: NoteItem) {
        dao.deleteItem(item)
    }

    override fun getAllItems(): Flow<List<NoteItem>> {
        return dao.getAllItems()
    }

    override suspend fun getNoteItemById(id: Int): NoteItem {
        return dao.getNoteItemById(id)
    }
}