package com.example.worddictionary.view.ui.screens.word_insert

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.worddictionary.R
import com.example.worddictionary.view.Screen

@Composable
fun WordInsertScreen(
    viewModel: WordInsertViewModel = hiltViewModel(),
    navController: NavController
) {
    val localFocusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    BackHandler(onBack = {
        navController.popBackStack()
        navController.navigate(Screen.DictionaryScreen.route)
    })

    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable(
                interactionSource = interactionSource,
                indication = null
            ) { localFocusManager.clearFocus() },
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = viewModel.engWord.value,
            maxLines = 1,
            onValueChange = { viewModel.setEngWord(it) },
            label = {
                Text(
                    text = stringResource(R.string.eng_word)
                )
            })


        OutlinedTextField(
            value = viewModel.czWord.value,
            maxLines = 1,
            onValueChange = { viewModel.setCzWord(it) },
            label = {
                Text(
                    text = stringResource(R.string.cz_word)
                )
            })


        OutlinedTextField(
            modifier = Modifier.size(300.dp),
            value = viewModel.meaning.value,
            onValueChange = { viewModel.setMeaning(it) },
            label = {
                Text(
                    text = stringResource(R.string.meaning)
                )
            }
        )


        Button(
            onClick = {
                viewModel.insertToDictionaryDatabase()
                navController.popBackStack()
                navController.navigate(Screen.DictionaryScreen.route)
            },
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(R.string.add_to_dictionary), style = TextStyle(color = Color.Black))
        }
    }

}