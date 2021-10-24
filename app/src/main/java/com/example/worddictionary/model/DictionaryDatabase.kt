package com.example.worddictionary.model

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [DictEntryEntity::class], version = 1)
abstract class DictionaryDatabase: RoomDatabase() {
    abstract fun dictDao():DictEntryDAO
}