package io.github.bozorgzadai.a17daysofgadgets

import android.content.res.Configuration
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.bozorgzadai.a17daysofgadgets.model.Gadget
import io.github.bozorgzadai.a17daysofgadgets.ui.theme.Shapes
import io.github.bozorgzadai.a17daysofgadgets.ui.theme._17DaysOfGadgetsTheme


@Composable
fun GadgetList(
    gadgets: List<Gadget>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    LazyColumn(
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
        modifier = modifier,
    ) {
        itemsIndexed(gadgets) { index, item ->
            GadgetItem(index, item)
        }
    }
}

@Composable
fun GadgetItem(index: Int, gadget: Gadget, modifier: Modifier = Modifier) {
    var isDesc by remember { mutableStateOf(false) }
    val color by animateColorAsState(
        targetValue = if (isDesc) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
    )

    Card(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.card_elevation)
        ),
        onClick = {
            isDesc = !isDesc
        },
        modifier = modifier
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .background(color = color)
                .padding(dimensionResource(R.dimen.padding_medium))
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioNoBouncy,
                        stiffness = Spring.StiffnessMedium
                    )
                )
        ) {
            Text(
                text = "Day ${index + 1}",
                style = MaterialTheme.typography.bodyLarge,
                fontSize = 16.sp,
            )
            Text(
                text = stringResource(gadget.title),
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .padding(
                        vertical = dimensionResource(R.dimen.padding_small)
                    )
            )
            Image(
                painter = painterResource(gadget.image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(Shapes.small)
                    .sizeIn(maxHeight = dimensionResource(R.dimen.image_size))
            )
            if (isDesc) {
                Text(
                    text = stringResource(gadget.desc),
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(
                            top = dimensionResource(R.dimen.padding_small),
                        )
                )
            }

        }
    }
}


@Preview("Light Theme")
@Preview("Dark Theme", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun GadgetListPreview() {
    _17DaysOfGadgetsTheme {
        GadgetsApp()
    }
}

