package com.example.worddictionary.view.ui.screens.word_detail

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.MutableTransitionState
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.worddictionary.R
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.view.Screen
import com.example.worddictionary.view.theme.*

@ExperimentalAnimationApi
@Composable
fun WordDetailScreen(
    viewModel: WordDetailViewModel = hiltViewModel(),
    navController: NavController,
    id: Int
) {
    val localFocusManager = LocalFocusManager.current
    val interactionSource = remember { MutableInteractionSource() }

    BackHandler(onBack = {
        navController.popBackStack(Screen.DictionaryScreen.route,true)
        navController.navigate(Screen.DictionaryScreen.route)
    })

    Box(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            floatingActionButton = {
                FloatingActionButton(
                    onClick = {
                        viewModel.setUpdatingEnabled(true)
                        viewModel.setDoneButtonEnabled(true)
                    },
                    elevation = FloatingActionButtonDefaults.elevation(12.dp),
                    backgroundColor = MainOrange
                ) {
                    Icon(
                        imageVector = Icons.Default.Edit,
                        contentDescription = "Edit an entry",
                        tint = Color.Black
                    )
                }
            }, modifier = Modifier.clickable(
                interactionSource = interactionSource,
                indication = null
            ) {
                localFocusManager.clearFocus()
            }) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                if (viewModel.updatingEnabled.value) {

                    OutlinedTextField(
                        value = viewModel.engWord.value,
                        onValueChange = { viewModel.setEngWord(it) },
                        maxLines = 1,
                        label = {
                            Text(
                                stringResource(id = R.string.eng_word),
                                style = TextStyle(fontSize = 12.sp)
                            )
                        }
                    )

                } else {
                    Text(
                        text = stringResource(id = R.string.eng_word),
                        style = Typography.h4
                    )
                    Text(
                        text = viewModel.engWord.value,
                        style = Typography.body2
                    )
                }

                Divider()
                //

                if (viewModel.updatingEnabled.value) {

                    OutlinedTextField(
                        value = viewModel.czWord.value,
                        onValueChange = { viewModel.setCzWord(it) },
                        maxLines = 1,
                        label = {
                            Text(
                                stringResource(id = R.string.cz_word),
                                style = TextStyle(fontSize = 12.sp)
                            )
                        }
                    )

                } else {
                    Text(text = stringResource(id = R.string.cz_word), style = Typography.h4)
                    Text(
                        text = viewModel.czWord.value,
                        style = Typography.body2
                    )
                }

                Divider()
                //

                if (viewModel.updatingEnabled.value) {

                    OutlinedTextField(
                        modifier = Modifier.size(300.dp),
                        value = viewModel.meaning.value,
                        onValueChange = { viewModel.setMeaning(it) },
                        label = {
                            Text(
                                stringResource(id = R.string.meaning),
                                style = TextStyle(fontSize = 12.sp)
                            )
                        }
                    )
                } else {

                    Text(text = stringResource(id = R.string.meaning), style = Typography.h4)
                    Text(
                        modifier = Modifier.size(300.dp),
                        text = viewModel.meaning.value,
                        style = Typography.body2
                    )
                }
            }
        }

        AnimatedVisibility(
            visible = !viewModel.showDoneButton.value,
            enter = fadeIn(),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
                .offset(y = (-75).dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Button(
                onClick = {viewModel.setDeleteAlertDialogEnabled(true)}
            ) {
                Icon(
                    imageVector = Icons.Default.Delete,
                    tint = Color.Black,
                    contentDescription = "Delete this entry"
                )
            }
        }

        AnimatedVisibility(
            visible = viewModel.showDoneButton.value,
            enter = fadeIn(),
            modifier = Modifier
                .padding(12.dp)
                .align(Alignment.BottomEnd)
                .offset(y = (-75).dp)
                .clip(RoundedCornerShape(12.dp))
        ) {
            Button(
                onClick = {
                    viewModel.updateEntryById(
                        id,
                        viewModel.engWord.value,
                        viewModel.czWord.value,
                        viewModel.meaning.value
                    )
                    viewModel.setUpdatingEnabled(false)
                    viewModel.setDoneButtonEnabled(false)
                },
                colors = ButtonDefaults.buttonColors(backgroundColor = MainOrange)
            ) {
                Icon(
                    imageVector = Icons.Default.Done,
                    contentDescription = "Updating done.",
                    tint = Color.Black
                )
            }
        }

        AnimatedVisibility(
            visible = viewModel.showDeleteAlertDialog.value,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            AlertDialog(
                onDismissRequest = { viewModel.setDeleteAlertDialogEnabled(false) },
                title = {
                    Text(text = "Delete entry")
                },
                text = {
                    Text(text = "Do you really want to delete this entry from dictionary?")
                },
                confirmButton = {
                    Button(onClick = {
                        viewModel.setDeleteAlertDialogEnabled(false)
                        viewModel.deleteEntry(
                            DictEntryEntity(
                                id,
                                viewModel.engWord.value,
                                viewModel.czWord.value,
                                viewModel.meaning.value
                            )
                        )
                        navController.popBackStack(Screen.DictionaryScreen.route, true)
                        navController.navigate(Screen.DictionaryScreen.route)
                    }) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = "YES, DELETE")
                    }
                },
                dismissButton = {
                    Button(onClick = { viewModel.setDeleteAlertDialogEnabled(false) }) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "NO, CLOSE DIALOG"
                        )
                    }
                }
            )
        }
    }
}