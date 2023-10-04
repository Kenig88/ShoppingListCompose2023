package com.kenig.shoppinglistcompose2023.note_list_screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.data.NoteItem
import com.kenig.shoppinglistcompose2023.new_note_screen.NewNoteEvent
import com.kenig.shoppinglistcompose2023.ui.theme.BlueLight
import com.kenig.shoppinglistcompose2023.ui.theme.LightText
import com.kenig.shoppinglistcompose2023.ui.theme.Red
import com.kenig.shoppinglistcompose2023.utils.Routes

@Composable
fun UiNoteItem(
    titleColor: String,
    noteItem: NoteItem,
    onEvent: (NoteListEvent) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 5.dp,
                end = 5.dp,
                top = 5.dp
            )
            .clickable {
                onEvent(NoteListEvent.OnItemClick(
                    Routes.NEW_NOTE + "/${noteItem.id}"
                ))
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text( //title
                    modifier = Modifier
                        .padding(top = 5.dp, start = 10.dp)
                        .weight(1f),
                    text = noteItem.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    color = Color(android.graphics.Color.parseColor(titleColor))
                )
                Text( //description
                    modifier = Modifier.padding(
                        top = 5.dp,
                        end = 10.dp
                    ),
                    text = noteItem.time,
                    color = BlueLight,
                    fontSize = 12.sp
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 10.dp,
                            start = 10.dp,
                            bottom = 5.dp
                        )
                        .weight(1f),
                    text = noteItem.description,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    color = LightText
                )
                IconButton(
                    onClick = {
                        onEvent(NoteListEvent.OnShowDeleteDialog(noteItem))
                    }
                ) {
                    Icon(
                        modifier = Modifier.padding(end = 10.dp),
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = null,
                        tint = Red
                    )
                }
            }
        }
    }
}