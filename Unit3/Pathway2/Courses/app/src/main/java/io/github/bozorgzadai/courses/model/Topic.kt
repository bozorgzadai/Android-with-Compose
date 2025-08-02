package io.github.bozorgzadai.courses.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Topic(
    @StringRes val title: Int,
    val id: Int,
    @DrawableRes val image: Int,
)
