package com.example.worddictionary.view.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.worddictionary.model.DictEntryEntity
import com.example.worddictionary.repository.DictEntry
import com.example.worddictionary.view.theme.MainOrange
import com.example.worddictionary.view.theme.MainYellow
import com.example.worddictionary.view.theme.Typography

@Composable
fun DictionaryEntry(
    entry: DictEntryEntity,
    onEntryClick: () -> Unit
) {
    Box(modifier = Modifier
        .clip(RoundedCornerShape(12.dp))
        .clickable { onEntryClick() }
        .background(MainYellow),
        contentAlignment = Alignment.TopStart) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier
                    .weight(3f)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MainOrange)
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = entry.engWord, style = Typography.h1)
                Text(text = entry.czWord, style = Typography.h2)
            }
            Spacer(modifier = Modifier.size(12.dp))
            Column(
                modifier = Modifier.weight(4f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = entry.meaning,
                    style = Typography.h3,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
    Spacer(modifier = Modifier.size(8.dp))
}