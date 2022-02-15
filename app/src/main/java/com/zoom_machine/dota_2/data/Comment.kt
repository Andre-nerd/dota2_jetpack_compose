package com.zoom_machine.dota_2.data

import androidx.annotation.DrawableRes

data class Comment(
    val name: String,
    val data: String,
    val text: String,
    @DrawableRes val image: Int
)