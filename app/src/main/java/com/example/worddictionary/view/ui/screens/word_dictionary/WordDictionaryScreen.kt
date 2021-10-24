package com.example.worddictionary.view.ui.screens.word_dictionary

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.worddictionary.R
import com.example.worddictionary.view.Screen
import com.example.worddictionary.view.theme.*
import com.example.worddictionary.view.ui.components.DictionaryEntry

@Composable
fun WordDictionaryScreen(
    viewModel: WordDictionaryViewModel = hiltViewModel(),
    navController: NavController
) {

    Scaffold(floatingActionButton = {
        FloatingActionButton(
            onClick = {
                navController.popBackStack()
                navController.navigate(
                    Screen.WordInsertScreen.route
                ) {

                }
            },
            backgroundColor = MainRed,
            elevation = FloatingActionButtonDefaults.elevation(20.dp),
        ) {
            Icon(
                modifier = Modifier.size(30.dp),
                imageVector = Icons.Default.Create,
                contentDescription = "Add a new entry",
                tint = Color.White
            )
        }
    }) {
        Column(modifier = Modifier.fillMaxSize()) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MainOrange)
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center

            ) {
                Text(
                    text = stringResource(R.string.my_english_dictionary),
                    style = Typography.body1,
                    color = Color.Black
                )
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                LazyColumn(modifier = Modifier.fillMaxSize()) {
                    items(viewModel.dictList.value) { word ->
                        DictionaryEntry(
                            word,
                            onEntryClick = {
                                viewModel.getEntryByPrimaryKeyImpl(word.id)
                                navController.navigate(Screen.WordDetailScreen.withArgs(viewModel.clickedEntryId.value))
                            })
                    }
                }
            }
        }
    }

}