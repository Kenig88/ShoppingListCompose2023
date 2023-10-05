package com.kenig.shoppinglistcompose2023.shopping_list_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.core.graphics.ColorUtils
import androidx.hilt.navigation.compose.hiltViewModel
import com.kenig.shoppinglistcompose2023.R
import com.kenig.shoppinglistcompose2023.data.ShoppingListItem
import com.kenig.shoppinglistcompose2023.settings_screen.ColorsUtils
import com.kenig.shoppinglistcompose2023.ui.theme.*
import com.kenig.shoppinglistcompose2023.utils.ProgressHelper
import com.kenig.shoppinglistcompose2023.utils.Routes

@Composable
fun UiShoppingListItem(
    titleColor: String,
    item: ShoppingListItem,
    onEvent: (ShoppingListEvent) -> Unit,
) {
    val progress = ProgressHelper.getProgress(
        item.allItemsCount,
        item.allSelectedItemsCount
    )

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
                }
                .clickable {
                    onEvent(
                        ShoppingListEvent.OnItemClick(
                            Routes.ADD_ITEM + "/${item.id}" //передаю id конкретного айтема в MainNavGraph
                        )
                    )
                },
            shape = RoundedCornerShape(10.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text( //заголовок
                    text = item.name,
                    color = Color(android.graphics.Color.parseColor(titleColor)),
                    fontWeight = Bold,
                    fontSize = 16.sp,
                    maxLines = 1
                )
                Spacer(modifier = Modifier.height(3.dp)) //либо использовать Padding у текста снизу
                Text( //время
                    text = item.time,
                    color = LightText,
                    fontSize = 13.sp
                )
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp), //либо использовать Spacer над прогресс-баром
                    progress = progress,
                    color = ColorsUtils.getProgressColor(progress)
                )
            }
        }

        IconButton( //deleteButton
            onClick = {
                onEvent(ShoppingListEvent.OnShowDeleteDialog(item))
            },
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

        IconButton( //editButton
            onClick = {
                onEvent(ShoppingListEvent.OnShowEditDialog(item))
            },
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
                text = "${item.allItemsCount}/${item.allSelectedItemsCount}",
                color = Color.White,
                modifier = Modifier.padding(5.dp),
                textAlign = TextAlign.Center,
            )
        }
    }
}