package io.github.bozorgzadai.paristourapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Category (
    val id: Long,
    @StringRes val title: Int,
    @DrawableRes val img: Int,
    val list: List<Recommendation>,
)