package io.github.bozorgzadai.bookshelf

import io.github.bozorgzadai.bookshelf.data.NetworkBooksRepository
import io.github.bozorgzadai.bookshelf.fake.FakeBookApiService
import io.github.bozorgzadai.bookshelf.fake.FakeDataSource
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class NetworkBooksRepositoryTest {

    @Test
    fun networkBooksRepository_getBooks_verifyBookList() =
        runTest {
            val repository = NetworkBooksRepository(
                booksApiService = FakeBookApiService()
            )
            assertEquals(
                FakeDataSource.fakeBooksResponse.items,
                repository.getBooks()
            )
        }
}
