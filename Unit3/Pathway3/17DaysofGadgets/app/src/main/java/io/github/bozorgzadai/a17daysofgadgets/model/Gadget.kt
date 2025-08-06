package io.github.bozorgzadai.a17daysofgadgets.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Gadget(
    @StringRes val title: Int,
    @DrawableRes val image: Int,
    @StringRes val desc: Int
)