package io.github.bozorgzadai.paristourapp.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.local.LocalCoffeesDataProvider
import io.github.bozorgzadai.paristourapp.data.model.Recommendation
import io.github.bozorgzadai.paristourapp.ui.theme.ParisTourAppTheme
import io.github.bozorgzadai.paristourapp.utils.ParisTourContentType

@Composable
fun RecommendationScreen(
    recommendationList: List<Recommendation>,
    currentRecommendationDetail: Recommendation,
    contentType: ParisTourContentType,
    onBackPressed: () -> Unit,
    onItemClicked: (Recommendation) -> Unit,
    setTitleList: () -> Unit,
    setTitleListDetail: (Recommendation) -> Unit,
    isShowingListPage: Boolean,
    modifier: Modifier = Modifier,
) {
    BackHandler {
        onBackPressed()
    }
    if (contentType == ParisTourContentType.ListAndDetail) {
        setTitleListDetail(currentRecommendationDetail)
        RecommendationListDetail(
            recommendationList = recommendationList,
            currentRecommendationDetail = currentRecommendationDetail,
            onItemClicked = onItemClicked,
            modifier = modifier,
        )
    } else {
        if (isShowingListPage) {
            setTitleList()
            RecommendationList(
                recommendations = recommendationList,
                onItemClicked = onItemClicked,
                modifier = modifier,
            )
        } else {
            setTitleListDetail(currentRecommendationDetail)
            RecommendationDetail(
                selectedRecommendation = currentRecommendationDetail,
            )
        }
    }

}

@Composable
fun RecommendationListDetail(
    recommendationList: List<Recommendation>,
    currentRecommendationDetail: Recommendation,
    onItemClicked: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
) {
    Row {
        RecommendationList(
            recommendations = recommendationList,
            onItemClicked = onItemClicked,
            modifier = modifier
                .weight(0.4f),
        )
        RecommendationDetail(
            selectedRecommendation = currentRecommendationDetail,
            modifier = modifier
                .weight(0.6f),
        )
    }
}

@Composable
fun RecommendationList(
    recommendations: List<Recommendation>,
    onItemClicked: (Recommendation) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        modifier = modifier
            .padding(
                start = dimensionResource(R.dimen.padding_small),
                end = dimensionResource(R.dimen.padding_small),
                top = dimensionResource(R.dimen.padding_medium),
            ),
    ) {
        items(
            items = recommendations,
            key = { recommendation -> recommendation.id }
        ) { recommendation ->
            RecommendationListItem(
                recommendation = recommendation,
                onItemClick = onItemClicked
            )
        }
    }
}


@Composable
private fun RecommendationListItem(
    recommendation: Recommendation,
    onItemClick: (Recommendation) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        elevation = CardDefaults.cardElevation(),
        modifier = modifier,
        shape = RoundedCornerShape(dimensionResource(R.dimen.card_corner_radius)),
        onClick = { onItemClick(recommendation) }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .size(dimensionResource(R.dimen.card_image_height))
        ) {
            RecommendationListImageItem(
                recommendation = recommendation,
                modifier = Modifier.size(dimensionResource(R.dimen.card_image_height))
            )
            Column(
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small),
                        horizontal = dimensionResource(R.dimen.padding_medium),
                    )
                    .weight(1f)
                    .align(alignment = Alignment.CenterVertically)
            ) {
                Text(
                    text = stringResource(recommendation.title),
                    style = MaterialTheme.typography.titleLarge,
                )
                Spacer(Modifier.height(dimensionResource(R.dimen.padding_medium)))
                Text(
                    text = stringResource(recommendation.desc),
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}


@Composable
private fun RecommendationListImageItem(
    recommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(recommendation.img),
            contentDescription = null,
            alignment = Alignment.Center,
            contentScale = ContentScale.Crop
        )
    }
}


@Composable
fun RecommendationDetail(
    selectedRecommendation: Recommendation,
    modifier: Modifier = Modifier
) {
    val scrollState = rememberScrollState()
    Column(
        modifier = modifier
            .verticalScroll(state = scrollState)
            .padding(
                dimensionResource(R.dimen.padding_medium)
            )
    ) {
        Image(
            painter = painterResource(selectedRecommendation.img),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sizeIn(maxHeight = 500.dp)
                .fillMaxWidth()
        )
        Text(
            text = stringResource(selectedRecommendation.desc),
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier
                .padding(
                    vertical = dimensionResource(R.dimen.padding_medium),
                    horizontal = dimensionResource(R.dimen.padding_medium)
                )
        )
    }
}


@Preview
@Composable
fun PreviewRecommendationFullScreen() {
    ParisTourAppTheme {
        RecommendationScreen(
            recommendationList = LocalCoffeesDataProvider.getCoffeeData(),
            currentRecommendationDetail = LocalCoffeesDataProvider.getCoffeeData()[0],
            contentType = ParisTourContentType.ListOnly,
            onBackPressed = {},
            onItemClicked = {},
            setTitleList = {},
            setTitleListDetail = {},
            isShowingListPage = true,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}

