package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight.Companion.Bold
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.ui.theme.*

@Preview(showBackground = true)
@Composable
fun UiShoppingListItem() {
    ConstraintLayout(
        modifier = Modifier.padding(start = 3.dp, end = 3.dp, top = 1.dp) // top!!??????
    ) {
        val (card, counter, editButton, deleteButton) = createRefs()

        Card(
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(card) {
                    top.linkTo(parent.top)
                    bottom.linkTo(parent.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                },
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "List 1",
                    color = DarkText,
                    fontWeight = Bold,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.height(3.dp)) //либо использовать Padding у текста снизу
                Text(
                    text = "07.09.2023 23:53",
                    color = LightText,
                    fontSize = 13.sp
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp) //либо использовать Spacer над прогресс-баром
                )
            }
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(deleteButton) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(card.end)
                }
                .padding(5.dp)
                .size(30.dp)

        ) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Red)
                    .padding(5.dp),
                tint = Color.White
            )
        }
        IconButton(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .constrainAs(editButton) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(deleteButton.start)
                }
                .padding(5.dp)
                .size(30.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_edit),
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Green)
                    .padding(5.dp),
                tint = Color.White
            )
        }
        Card(
            shape = RoundedCornerShape(5.dp),
            modifier = Modifier
                .constrainAs(counter) {
                    top.linkTo(card.top)
                    bottom.linkTo(card.top)
                    end.linkTo(editButton.start)
                }
                .padding(end = 5.dp),
            backgroundColor = BlueLight

        ) {
            Text(
                text = "2/2",
                color = Color.White,
                modifier = Modifier.padding(5.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}