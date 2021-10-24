package com.example.worddictionary.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.worddictionary.repository.DictEntry

@Entity(tableName = "dictionary")
data class DictEntryEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    var engWord: String,
    var czWord: String,
    var meaning: String
)

