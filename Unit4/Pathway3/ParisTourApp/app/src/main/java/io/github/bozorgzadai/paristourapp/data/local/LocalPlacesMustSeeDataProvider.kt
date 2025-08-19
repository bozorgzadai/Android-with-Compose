package io.github.bozorgzadai.paristourapp.data.local

import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.model.Recommendation


object LocalPlacesMustSeeDataProvider {
    val defaultPlacesMustSee = getPlacesMustSeeData()[0]

    fun getPlacesMustSeeData(): List<Recommendation> {
        return listOf(
            Recommendation(
                id = 1L,
                title = R.string.must_see_name_1,
                desc = R.string.must_see_desc_1,
                img = R.drawable.must_see_1,
            ),
            Recommendation(
                id = 2L,
                title = R.string.must_see_name_2,
                desc = R.string.must_see_desc_2,
                img = R.drawable.must_see_2,
            ),
            Recommendation(
                id = 3L,
                title = R.string.must_see_name_3,
                desc = R.string.must_see_desc_3,
                img = R.drawable.must_see_3,
            ),
            Recommendation(
                id = 4L,
                title = R.string.must_see_name_4,
                desc = R.string.must_see_desc_4,
                img = R.drawable.must_see_4,
            ),
            Recommendation(
                id = 5L,
                title = R.string.must_see_name_5,
                desc = R.string.must_see_desc_5,
                img = R.drawable.must_see_5,
            ),
            Recommendation(
                id = 6L,
                title = R.string.must_see_name_6,
                desc = R.string.must_see_desc_6,
                img = R.drawable.must_see_6,
            ),
            Recommendation(
                id = 7L,
                title = R.string.must_see_name_7,
                desc = R.string.must_see_desc_7,
                img = R.drawable.must_see_7,
            ),
            Recommendation(
                id = 8L,
                title = R.string.must_see_name_8,
                desc = R.string.must_see_desc_8,
                img = R.drawable.must_see_8,
            ),
            Recommendation(
                id = 9L,
                title = R.string.must_see_name_9,
                desc = R.string.must_see_desc_9,
                img = R.drawable.must_see_9,
            ),
            Recommendation(
                id = 10L,
                title = R.string.must_see_name_10,
                desc = R.string.must_see_desc_10,
                img = R.drawable.must_see_10,
            ),

            )

    }
}