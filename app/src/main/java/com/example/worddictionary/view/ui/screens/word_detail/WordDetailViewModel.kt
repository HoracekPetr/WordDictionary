package com.example.worddictionary.view.ui.screens.word_detail

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.repository.DictRepositoryImpl
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WordDetailViewModel @Inject constructor(
    private val dictRepositoryImpl: DictRepositoryImpl,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val id: Int? = savedStateHandle.get("id")

    private var _entry = mutableStateOf(DictEntryEntity(engWord = "", czWord = "", meaning = ""))
    var entry: State<DictEntryEntity> = _entry

    private var _engWord = mutableStateOf("")
    var engWord: State<String> = _engWord

    private var _czWord = mutableStateOf("")
    var czWord: State<String> = _czWord

    private var _meaning = mutableStateOf("")
    var meaning: State<String> = _meaning

    private var _updatingEnabled = mutableStateOf(false)
    var updatingEnabled: State<Boolean> = _updatingEnabled

    private var _showDoneButton = mutableStateOf(false)
    var showDoneButton: State<Boolean> = _showDoneButton

    private var _showDeleteAlertDialog = mutableStateOf(false)
    var showDeleteAlertDialog: State<Boolean> = _showDeleteAlertDialog

    init {
        loadEntryById(id ?: 1)
    }


    //REPOSITORY FUNCTIONS
    fun loadEntryById(id: Int) {
        viewModelScope.launch {
            _entry.value = dictRepositoryImpl.loadOneItem(id)
            setEngWord(entry.value.engWord)
            setCzWord(entry.value.czWord)
            setMeaning(entry.value.meaning)
        }
    }

    fun updateEntryById(id: Int, engWord: String, czWord: String, meaning: String) {
        viewModelScope.launch {
            dictRepositoryImpl.updateOneItem(id, engWord, czWord, meaning)
        }
    }

    fun deleteEntry(entry: DictEntryEntity) {
        viewModelScope.launch {
            dictRepositoryImpl.deleteDictEntry(entry)
        }
    }
    //SET FUNCTIONS


    fun setUpdatingEnabled(enabled: Boolean) {
        _updatingEnabled.value = enabled
    }

    fun setDoneButtonEnabled(enabled: Boolean) {
        _showDoneButton.value = enabled
    }

    fun setDeleteAlertDialogEnabled(enabled: Boolean) {
        _showDeleteAlertDialog.value = enabled
    }

    fun setEngWord(engWord: String) {
        _engWord.value = engWord
    }

    fun setCzWord(czWord: String) {
        _czWord.value = czWord
    }

    fun setMeaning(meaning: String) {
        _meaning.value = meaning
    }


}
