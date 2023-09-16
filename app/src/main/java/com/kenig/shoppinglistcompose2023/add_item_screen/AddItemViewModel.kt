package com.kenig.shoppinglistcompose2023.add_item_screen

import androidx.lifecycle.ViewModel
import com.kenig.shoppinglistcompose2023.data.AddItemRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddItemViewModel @Inject constructor(
    val repository: AddItemRepository
) : ViewModel() {


}