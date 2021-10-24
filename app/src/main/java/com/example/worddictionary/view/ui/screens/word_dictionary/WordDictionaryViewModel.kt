package com.example.worddictionary.view.ui.screens.word_dictionary

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.repository.DictRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class WordDictionaryViewModel @Inject constructor(
    private val dictRepositoryImpl: DictRepositoryImpl
) : ViewModel() {

    private var _dictList = mutableStateOf<List<DictEntryEntity>>(emptyList())
    var dictList: State<List<DictEntryEntity>> = _dictList

    private var _clickedEntryId = mutableStateOf(0)
    var clickedEntryId: State<Int> = _clickedEntryId

    init {
        getDictionaryDatabase()
    }

    //REPOSITORY FUNCTIONS

    private fun getDictionaryDatabase() {
        viewModelScope.launch {
            _dictList.value = dictRepositoryImpl.loadAll()
        }
    }

    private suspend fun getEntryByPrimaryKey(id: Int): Int {
        _clickedEntryId.value = dictRepositoryImpl.loadOneItem(id).id
        return _clickedEntryId.value
    }

    //SET FUNCTIONS

    fun getEntryByPrimaryKeyImpl(id: Int): Int = runBlocking(Dispatchers.IO) {
        getEntryByPrimaryKey(id)
    }
}