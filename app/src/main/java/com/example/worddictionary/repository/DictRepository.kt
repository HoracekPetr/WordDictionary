package com.example.worddictionary.repository

import com.example.worddictionary.model.DictEntryEntity

interface DictRepository {

    suspend fun insertDictEntry(entry: DictEntryEntity)
    suspend fun deleteDictEntry(entry: DictEntryEntity)
    suspend fun loadAll(): List<DictEntryEntity>
    suspend fun loadOneItem(id: Int): DictEntryEntity
    suspend fun updateOneItem(id: Int, engWord: String, czWord: String, meaning: String)
}