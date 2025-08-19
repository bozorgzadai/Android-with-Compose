package io.github.bozorgzadai.paristourapp.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.utils.ParisTourContentType


enum class ParisTourScreen(@StringRes val title: Int) {
    Start(title = R.string.app_name),
    RecommendationDetail(title = R.string.recommendation_detail_page),
}

@Composable
fun ParisTourApp(
    windowSize: WindowWidthSizeClass,
) {
    val navController: NavHostController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = ParisTourScreen.valueOf(
        backStackEntry?.destination?.route ?: ParisTourScreen.Start.name
    )
    val viewModel: ParisTourViewModel = viewModel()
    val uiState by viewModel.uiState.collectAsState()

    val contentType = when (windowSize) {
        WindowWidthSizeClass.Compact -> ParisTourContentType.ListOnly
        WindowWidthSizeClass.Medium -> ParisTourContentType.ListOnly
        WindowWidthSizeClass.Expanded -> ParisTourContentType.ListAndDetail
        else -> ParisTourContentType.ListOnly
    }

    Scaffold(
        topBar = {
            ParisTourAppBar(
                currentScreenTitle = uiState.appBarTitle,
                canNavigateBack = currentScreen.name != ParisTourScreen.Start.name,
                navigateUp = { viewModel.backButtonPressed(navController) }
            )
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ParisTourScreen.Start.name,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(route = ParisTourScreen.Start.name) {
                CategoryScreen(
                    categories = uiState.categoryList,
                    onCategoryClicked = {
                        viewModel.updateCurrentRecommendationList(it)
                        navController.navigate(ParisTourScreen.RecommendationDetail.name)
                    },
                    setTitleHomeScreen = {
                        viewModel.setTitleHomeScreen()
                    },
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            composable(route = ParisTourScreen.RecommendationDetail.name) {
                RecommendationScreen(
                    recommendationList = uiState.currentRecommendationList,
                    currentRecommendationDetail = uiState.currentRecommendationDetail,
                    contentType = contentType,
                    onBackPressed = {
                        viewModel.backButtonPressed(navController)
                    },
                    onItemClicked = {
                        viewModel.updateCurrentRecommendationDetail(it)
                    },
                    setTitleList = {
                        viewModel.setTitleList()
                    },
                    setTitleListDetail = {
                        viewModel.setTitleListDetail(it)
                    },
                    isShowingListPage = uiState.isShowingListPage,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParisTourAppBar(
    @StringRes currentScreenTitle: Int,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = { Text(stringResource(currentScreenTitle)) },
        modifier = modifier,
        navigationIcon = {
            if (canNavigateBack) {
                IconButton(onClick = navigateUp) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(R.string.back_button)
                    )
                }
            }
        }
    )
}