package com.example.worddictionary.di

import android.content.Context
import androidx.room.Room
import com.example.worddictionary.model.DictEntryDAO
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.model.DictionaryDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDictEntryDao(dictDatabase: DictionaryDatabase): DictEntryDAO {
        return dictDatabase.dictDao()
    }

    @Singleton
    @Provides
    fun provideDictionaryDatabase(@ApplicationContext appContext: Context): DictionaryDatabase {
        return Room.databaseBuilder(
            appContext,
            DictionaryDatabase::class.java,
            "dict-database")
            .build()
    }

}