package com.example.worddictionary.repository

import com.example.worddictionary.model.DictEntryDAO
import com.example.worddictionary.model.DictEntryEntity
import javax.inject.Inject

class DictRepositoryImpl @Inject constructor(
    private val dictEntryDAO: DictEntryDAO
):DictRepository {

    override suspend fun insertDictEntry(entry: DictEntryEntity) {
        dictEntryDAO.insertDictEntry(entry)
    }

    override suspend fun deleteDictEntry(entry: DictEntryEntity) {
        dictEntryDAO.deleteDictEntry(entry)
    }

    override suspend fun loadAll(): List<DictEntryEntity> {
        return dictEntryDAO.loadAll()
    }

    override suspend fun loadOneItem(id: Int):DictEntryEntity{
        return dictEntryDAO.loadOneItem(id)
    }

    override suspend fun updateOneItem(id: Int, engWord: String, czWord: String, meaning: String) {
        dictEntryDAO.updateOneItem(id, engWord, czWord, meaning)
    }
}