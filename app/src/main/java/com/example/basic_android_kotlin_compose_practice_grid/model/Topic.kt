package com.example.basic_android_kotlin_compose_practice_grid.model

import android.media.Image
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

class Topic(
    @StringRes val stringId: Int,
    val courseCountId: Int,
    @DrawableRes val imageId: Int) {
}