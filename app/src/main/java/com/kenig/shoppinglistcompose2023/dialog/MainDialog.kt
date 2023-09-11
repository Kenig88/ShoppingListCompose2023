package com.kenig.shoppinglistcompose2023.dialog

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.ui.theme.DarkText
import com.kenig.shoppinglistcompose2023.ui.theme.Red
import com.kenig.shoppinglistcompose2023.ui.theme.RedDialog

@Composable
fun MainDialog(dialogController: DialogController) {
    if (dialogController.openDialog.value) {
        AlertDialog(
            onDismissRequest = {
                dialogController.onDialogEvent(DialogEvent.OnCancelButton)
            },
            title = null,
            text = {
                Column(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = dialogController.dialogTitle.value,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        color = DarkText
                    )
                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )
                    if (dialogController.showEditableText.value) {
                        TextField(
                            shape = RoundedCornerShape(5.dp),
                            value = dialogController.editableText.value,
                            onValueChange = { text -> //постоянно обновляет текст который пишу
                                dialogController.onDialogEvent(DialogEvent.OnTextChange(text))
                            },
                            label = {
                                Text(text = stringResource(R.string.dialog_label))
                            },
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = Color.Gray,
                                focusedIndicatorColor = Color.Transparent,
                                unfocusedIndicatorColor = Color.Transparent
                            ),
                            singleLine = true,
                            textStyle = TextStyle(
                                color = DarkText,
                                fontSize = 18.sp
                            )
                        )
                    }
                }
            },
            confirmButton = {
                Button(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnConfirmButton)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.dialog_confirm_button),
                        color = Color.White
                    )
                }
            },
            dismissButton = {
                Button(
                    onClick = {
                        dialogController.onDialogEvent(DialogEvent.OnCancelButton)
                    },
                    colors = ButtonDefaults.buttonColors(Color.Black)
                ) {
                    Text(
                        text = stringResource(R.string.dialog_cancel_button),
                        color = Color.White
                    )
                }
            },
            shape = RoundedCornerShape(10.dp),
            backgroundColor = RedDialog
        )
    }
}