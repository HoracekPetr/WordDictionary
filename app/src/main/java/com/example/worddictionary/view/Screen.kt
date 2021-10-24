package com.example.worddictionary.view

sealed class Screen(val route: String) {
    object DictionaryScreen: Screen("dictionary_screen")
    object WordInsertScreen: Screen("word_insert_screen")
    object WordDetailScreen: Screen("word_detail_screen")

    fun withArgs(vararg args: Int): String{
        return buildString {
            append(route)
            args.forEach {
                arg -> append("/${arg}")
            }
        }
    }
}