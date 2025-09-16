package io.github.bozorgzadai.bookshelf.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import io.github.bozorgzadai.bookshelf.BooksApplication
import io.github.bozorgzadai.bookshelf.data.BooksRepository
import io.github.bozorgzadai.bookshelf.model.Book
import kotlinx.coroutines.launch
import retrofit2.HttpException
import java.io.IOException

/**
 * UI state for the Home screen
 */
sealed interface BookUiState {
    data class Success(val books: List<Book>) : BookUiState
    object Error : BookUiState
    object Loading : BookUiState
}

class BookViewModel(private val booksRepository: BooksRepository) : ViewModel() {
    /** The mutable State that stores the status of the most recent request */
    var bookUiState: BookUiState by mutableStateOf(BookUiState.Loading)
        private set

    /**
     * Call getBooks() on init so we can display status immediately.
     */
    init {
        getBooks()
    }

    /**
     * Gets Books information from the Books API Retrofit service and updates the
     * [Book] [List] [MutableList].
     */
    fun getBooks() {
        viewModelScope.launch {
            bookUiState = BookUiState.Loading
            bookUiState = try {
                BookUiState.Success(
                    booksRepository.getBooks()
                )
            } catch (e: IOException) {
                BookUiState.Error
            } catch (e: HttpException) {
                BookUiState.Error
            }
        }
    }

    /**
     * Factory for [BookViewModel] that takes [BooksRepository] as a dependency
     */
    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as BooksApplication)
                val booksRepository = application.container.booksRepository
                BookViewModel(booksRepository = booksRepository)
            }
        }
    }
}
