package io.github.bozorgzadai.bookshelf

import io.github.bozorgzadai.bookshelf.fake.FakeDataSource
import io.github.bozorgzadai.bookshelf.fake.FakeNetworkBooksRepository
import io.github.bozorgzadai.bookshelf.rules.TestDispatcherRule
import io.github.bozorgzadai.bookshelf.ui.screens.BookUiState
import io.github.bozorgzadai.bookshelf.ui.screens.BookViewModel
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.runTest
import org.junit.Rule
import org.junit.Test

class BooksViewModelTest {

    @get:Rule
    val testDispatcher = TestDispatcherRule()

    @Test
    fun bookViewModel_getBooks_verifyBookUiStateSuccess() =
        runTest {
            val bookViewModel = BookViewModel(
                booksRepository = FakeNetworkBooksRepository()
            )
            assertEquals(
                BookUiState.Success(
                    FakeDataSource.fakeBooksResponse.items
                ),
                bookViewModel.bookUiState
            )
        }
}