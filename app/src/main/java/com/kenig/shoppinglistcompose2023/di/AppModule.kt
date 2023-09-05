package com.kenig.shoppinglistcompose2023.di

import android.app.Application
import androidx.room.Room
import com.kenig.shoppinglistcompose2023.data.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton //создастся только 1 инстанция базы данных
    fun provideMainDb(app: Application): MainDb {
        return Room.databaseBuilder(
            app, //"app" это контекст (application context)
            MainDb::class.java,
            "shop_list_db"
        ).build()
    }

    @Provides
    @Singleton //создастся только 1 инстанция данного класса
    fun provideShopRepo(db: MainDb): ShoppingListRepository {
        return ShoppingListRepoImpl(db.shoppingListDao)
    }

    @Provides
    @Singleton //создастся только 1 инстанция данного класса
    fun provideAddItemRepo(db: MainDb): AddItemRepository {
        return AddItemRepoImpl(db.addItemDao)
    }

    @Provides
    @Singleton //создастся только 1 инстанция данного класса
    fun provideNoteRepo(db: MainDb): NoteRepository {
        return NoteItemRepoImpl(db.noteDao)
    }
}