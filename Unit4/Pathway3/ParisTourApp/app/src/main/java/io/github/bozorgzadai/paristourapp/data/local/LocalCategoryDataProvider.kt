package io.github.bozorgzadai.paristourapp.data.local

import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.model.Category


object LocalCategoryDataProvider {
    val defaultCategory = getCategoryData()[0]

    fun getCategoryData(): List<Category> {
        return listOf(
            Category(
                id = 1L,
                title = R.string.coffee_category,
                img = R.drawable.coffee_shop_1,
                list = LocalCoffeesDataProvider.getCoffeeData()
            ),
            Category(
                id = 2L,
                title = R.string.park_category,
                img = R.drawable.parks_1,
                list = LocalParksDataProvider.getParksData()
            ),
            Category(
                id = 3L,
                title = R.string.places_must_see_category,
                img = R.drawable.must_see_1,
                list = LocalPlacesMustSeeDataProvider.getPlacesMustSeeData()
            ),
            Category(
                id = 4L,
                title = R.string.restaurant_category,
                img = R.drawable.restaurants_4,
                list = LocalRestaurantDataProvider.getRestaurantData()
            ),
            Category(
                id = 5L,
                title = R.string.shopping_center_category,
                img = R.drawable.shopping_center_3,
                list = LocalShoppingCenterDataProvider.getShoppingCenterData()
            )

        )
    }
}