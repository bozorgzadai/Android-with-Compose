package io.github.bozorgzadai.paristourapp.data.local

import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.model.Recommendation


object LocalShoppingCenterDataProvider {
    val defaultShoppingCenter = getShoppingCenterData()[0]

    fun getShoppingCenterData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1L,
                title = R.string.shopping_center_name_1,
                desc = R.string.shopping_center_desc_1,
                img = R.drawable.shopping_center_1,
            ),
            Recommendation(
                id = 2L,
                title = R.string.shopping_center_name_2,
                desc = R.string.shopping_center_desc_2,
                img = R.drawable.shopping_center_2,
            ),
            Recommendation(
                id = 3L,
                title = R.string.shopping_center_name_3,
                desc = R.string.shopping_center_desc_3,
                img = R.drawable.shopping_center_3,
            ),
            Recommendation(
                id = 4L,
                title = R.string.shopping_center_name_4,
                desc = R.string.shopping_center_desc_4,
                img = R.drawable.shopping_center_4,
            ),
            Recommendation(
                id = 5L,
                title = R.string.shopping_center_name_5,
                desc = R.string.shopping_center_desc_5,
                img = R.drawable.shopping_center_5,
            ),
            Recommendation(
                id = 6L,
                title = R.string.shopping_center_name_6,
                desc = R.string.shopping_center_desc_6,
                img = R.drawable.shopping_center_6,
            ),
            Recommendation(
                id = 7L,
                title = R.string.shopping_center_name_7,
                desc = R.string.shopping_center_desc_7,
                img = R.drawable.shopping_center_7,
            ),
            Recommendation(
                id = 8L,
                title = R.string.shopping_center_name_8,
                desc = R.string.shopping_center_desc_8,
                img = R.drawable.shopping_center_8,
            ),
            Recommendation(
                id = 9L,
                title = R.string.shopping_center_name_9,
                desc = R.string.shopping_center_desc_9,
                img = R.drawable.shopping_center_9,
            ),
            Recommendation(
                id = 10L,
                title = R.string.shopping_center_name_10,
                desc = R.string.shopping_center_desc_10,
                img = R.drawable.shopping_center_10,
            ),

            )
    }
}