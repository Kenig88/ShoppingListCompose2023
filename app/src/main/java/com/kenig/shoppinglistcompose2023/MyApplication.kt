package com.kenig.shoppinglistcompose2023

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp //теперь будет использован именно этот унаследованный класс от Application для Dagger
class MyApplication : Application() {
}