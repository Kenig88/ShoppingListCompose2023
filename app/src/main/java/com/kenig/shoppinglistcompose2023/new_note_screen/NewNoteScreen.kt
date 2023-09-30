package com.kenig.shoppinglistcompose2023.new_note_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.ui.theme.BlueLight
import com.kenig.shoppinglistcompose2023.ui.theme.DarkText
import com.kenig.shoppinglistcompose2023.ui.theme.GrayLight

@Composable
fun NewNoteScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(GrayLight)
    ) {
        Card(
            modifier = Modifier
                .fillMaxSize()
                .padding(3.dp),
            shape = RoundedCornerShape(5.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    TextField(
                        modifier = Modifier.weight(1f),
                        value = "",
                        onValueChange = {},
                        label = {
                            Text(
                                text = stringResource(R.string.text_field_title)
                            )
                        },
                        singleLine = true,
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = Color.White,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = BlueLight
                        ),
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = DarkText
                        )
                    )
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_save),
                            contentDescription = null,
                            tint = BlueLight
                        )
                    }
                }
                Spacer(modifier = Modifier.height(5.dp))
                TextField(
                    modifier = Modifier.fillMaxSize(),
                    value = "",
                    onValueChange = {

                    },
                    label = {
                        Text(
                            text = stringResource(R.string.text_field_description)
                        )
                    }
                )
            }
        }
    }
}