package io.github.bozorgzadai.paristourapp.data.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Recommendation(
    val id: Long,
    @StringRes val title: Int,
    @StringRes val desc: Int,
    @DrawableRes val img: Int,
)