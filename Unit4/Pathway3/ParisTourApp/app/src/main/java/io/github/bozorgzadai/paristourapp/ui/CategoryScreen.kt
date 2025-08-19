package io.github.bozorgzadai.paristourapp.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.local.LocalCategoryDataProvider
import io.github.bozorgzadai.paristourapp.data.model.Category
import io.github.bozorgzadai.paristourapp.ui.theme.ParisTourAppTheme

@Composable
fun CategoryScreen(
    categories: List<Category>,
    onCategoryClicked: (Category) -> Unit,
    setTitleHomeScreen: () -> Unit,
    modifier: Modifier = Modifier,
) {
    setTitleHomeScreen()
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_medium),
            ),
    ) {
        items(categories, key = { category -> category.id }) { category ->
            CategoryListItem(
                category = category,
                onItemClick = onCategoryClicked
            )
        }
    }
}


@Composable
private fun CategoryListItem(
    category: Category,
    onItemClick: (Category) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(category) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            CategoryListImageItem(
                category = category,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium)
                    )
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(category.title),
                    style = MaterialTheme.typography.titleLarge,
                )
            }
        }
    }
}


@Composable
private fun CategoryListImageItem(
    category: Category,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(category.img),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}

@Preview
@Composable
fun PreviewCategoryScreen() {
    ParisTourAppTheme {
        CategoryScreen(
            categories = LocalCategoryDataProvider.getCategoryData(),
            onCategoryClicked = {},
            setTitleHomeScreen = {},
            modifier = Modifier
                .fillMaxSize()
        )
    }
}