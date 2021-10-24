package com.example.worddictionary.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.*
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.worddictionary.view.theme.WordDictionaryTheme
import com.example.worddictionary.view.ui.screens.word_detail.WordDetailScreen
import com.example.worddictionary.view.ui.screens.word_insert.WordInsertScreen
import com.example.worddictionary.view.ui.screens.word_dictionary.WordDictionaryScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @ExperimentalAnimationApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WordDictionaryTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()

                    NavHost(
                        navController = navController,
                        startDestination = Screen.DictionaryScreen.route
                    ) {
                        composable(
                            route = Screen.DictionaryScreen.route
                        ){
                            WordDictionaryScreen(navController = navController)
                        }

                        composable(
                            route = Screen.WordInsertScreen.route
                        ){
                            WordInsertScreen(navController = navController)
                        }

                        composable(
                            route = Screen.WordDetailScreen.route + "/{id}",
                            arguments = listOf(
                                navArgument("id"){
                                    type = NavType.IntType
                                }
                            )
                        ){ wordDetail ->
                            WordDetailScreen(navController = navController, id = wordDetail.arguments?.getInt("id") ?: 1)
                        }
                    }

                }
            }
        }
    }
}
