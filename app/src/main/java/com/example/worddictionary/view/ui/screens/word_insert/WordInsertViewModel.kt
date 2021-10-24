package com.example.worddictionary.view.ui.screens.word_insert

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.repository.DictRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordInsertViewModel @Inject constructor(
    private val dictRepositoryImpl: DictRepositoryImpl
): ViewModel() {

    private var _engWord = mutableStateOf("")
    var engWord: State<String> = _engWord

    private var _czWord = mutableStateOf("")
    var czWord: State<String> = _czWord

    private var _meaning = mutableStateOf("")
    var meaning: State<String> = _meaning

    //REPOSITORY FUNCTIONS

    fun insertToDictionaryDatabase(){
        viewModelScope.launch{
            dictRepositoryImpl.insertDictEntry(DictEntryEntity(engWord = _engWord.value, czWord = _czWord.value, meaning = _meaning.value))
        }
    }


    //Set Functions

    fun setEngWord(engWord: String){
        _engWord.value = engWord
    }

    fun setCzWord(czWord: String){
        _czWord.value = czWord
    }

    fun setMeaning(meaning: String){
        _meaning.value = meaning
    }

}