package io.github.bozorgzadai.paristourapp.data.local

import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.model.Recommendation


object LocalRestaurantDataProvider {
    val defaultRestaurant = getRestaurantData()[0]

    fun getRestaurantData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1L,
                title = R.string.restaurant_name_1,
                desc = R.string.restaurant_desc_1,
                img = R.drawable.restaurants_1,
            ),
            Recommendation(
                id = 2L,
                title = R.string.restaurant_name_2,
                desc = R.string.restaurant_desc_2,
                img = R.drawable.restaurants_2,
            ),
            Recommendation(
                id = 3L,
                title = R.string.restaurant_name_3,
                desc = R.string.restaurant_desc_3,
                img = R.drawable.restaurants_3,
            ),
            Recommendation(
                id = 4L,
                title = R.string.restaurant_name_4,
                desc = R.string.restaurant_desc_4,
                img = R.drawable.restaurants_4,
            ),
            Recommendation(
                id = 5L,
                title = R.string.restaurant_name_5,
                desc = R.string.restaurant_desc_5,
                img = R.drawable.restaurants_5,
            ),
            Recommendation(
                id = 6L,
                title = R.string.restaurant_name_6,
                desc = R.string.restaurant_desc_6,
                img = R.drawable.restaurants_6,
            ),
            Recommendation(
                id = 7L,
                title = R.string.restaurant_name_7,
                desc = R.string.restaurant_desc_7,
                img = R.drawable.restaurants_7,
            ),
            Recommendation(
                id = 8L,
                title = R.string.restaurant_name_8,
                desc = R.string.restaurant_desc_8,
                img = R.drawable.restaurants_8,
            )

        )

    }
}