package io.github.bozorgzadai.paristourapp.ui

import androidx.annotation.StringRes
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import io.github.bozorgzadai.paristourapp.R
import io.github.bozorgzadai.paristourapp.data.local.LocalCategoryDataProvider
import io.github.bozorgzadai.paristourapp.data.local.LocalCoffeesDataProvider
import io.github.bozorgzadai.paristourapp.data.model.Category
import io.github.bozorgzadai.paristourapp.data.model.Recommendation
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

data class ParisTourUiState(
    @StringRes val appBarTitle: Int = R.string.app_name,
    val categoryList: List<Category> = emptyList(),
    val currentRecommendationList: List<Recommendation> = emptyList(),
    val currentRecommendationDetail: Recommendation = LocalCoffeesDataProvider.defaultCoffee,
    val isShowingListPage: Boolean = true,
)

class ParisTourViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(
        ParisTourUiState(
            categoryList = LocalCategoryDataProvider.getCategoryData(),
        )
    )
    val uiState: StateFlow<ParisTourUiState> = _uiState
    private var currentSelectedCategory = LocalCoffeesDataProvider.defaultCoffee.title

    fun setTitleHomeScreen() {
        _uiState.update {
            it.copy(
                appBarTitle = R.string.app_name,
            )
        }
    }

    fun setTitleList() {
        _uiState.update {
            it.copy(
                appBarTitle = currentSelectedCategory,
            )
        }
    }

    fun setTitleListDetail(recommendation: Recommendation) {
        _uiState.update {
            it.copy(
                appBarTitle = recommendation.title,
            )
        }
    }

    fun updateCurrentRecommendationList(selectedCategory: Category) {
        currentSelectedCategory = selectedCategory.title
        _uiState.update {
            it.copy(
                currentRecommendationList = selectedCategory.list,
                currentRecommendationDetail = selectedCategory.list[0]
            )
        }
    }

    fun updateCurrentRecommendationDetail(selectedRecommendation: Recommendation) {
        _uiState.update {
            it.copy(
                currentRecommendationDetail = selectedRecommendation,
                isShowingListPage = false
            )
        }
    }

    fun backButtonPressed(navController: NavHostController) {
        if (!_uiState.value.isShowingListPage) {
            _uiState.update {
                it.copy(
                    isShowingListPage = true,
                )
            }
            return
        }
        navController.navigateUp()
    }
}