package io.github.bozorgzadai.paristourapp.data.local

import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.model.Recommendation


object LocalCoffeesDataProvider {
    val defaultCoffee = getCoffeeData()[0]

    fun getCoffeeData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1L,
                title = R.string.coffee_shop_name_1,
                desc = R.string.coffee_shop_desc_1,
                img = R.drawable.coffee_shop_1,
            ),
            Recommendation(
                id = 2L,
                title = R.string.coffee_shop_name_2,
                desc = R.string.coffee_shop_desc_2,
                img = R.drawable.coffee_shop_2,
            ),
            Recommendation(
                id = 3L,
                title = R.string.coffee_shop_name_3,
                desc = R.string.coffee_shop_desc_3,
                img = R.drawable.coffee_shop_3,
            ),
            Recommendation(
                id = 4L,
                title = R.string.coffee_shop_name_4,
                desc = R.string.coffee_shop_desc_4,
                img = R.drawable.coffee_shop_4,
            ),
            Recommendation(
                id = 5L,
                title = R.string.coffee_shop_name_5,
                desc = R.string.coffee_shop_desc_5,
                img = R.drawable.coffee_shop_5,
            ),
            Recommendation(
                id = 6L,
                title = R.string.coffee_shop_name_6,
                desc = R.string.coffee_shop_desc_6,
                img = R.drawable.coffee_shop_6,
            ),
            Recommendation(
                id = 7L,
                title = R.string.coffee_shop_name_7,
                desc = R.string.coffee_shop_desc_7,
                img = R.drawable.coffee_shop_7,
            ),

            )
    }
}