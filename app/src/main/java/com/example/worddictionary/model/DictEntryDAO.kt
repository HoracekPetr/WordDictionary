package com.example.worddictionary.model

import androidx.room.*

@Dao
interface DictEntryDAO {

    @Insert
    suspend fun insertDictEntry(entry: DictEntryEntity)
    @Delete
    suspend fun deleteDictEntry(entry: DictEntryEntity)

    @Query("SELECT * from dictionary")
    suspend fun loadAll(): List<DictEntryEntity>

    @Query("SELECT * from dictionary WHERE id = (:itemId)")
    suspend fun loadOneItem(itemId: Int): DictEntryEntity

    @Query("UPDATE dictionary SET engWord = (:engWord), czWord = (:czWord), meaning = (:meaning) WHERE id = (:itemId)")
    suspend fun updateOneItem(itemId: Int, engWord: String, czWord: String, meaning: String)
}