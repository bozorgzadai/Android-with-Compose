package io.github.bozorgzadai.bookshelf.fake

import io.github.bozorgzadai.bookshelf.data.BooksRepository
import io.github.bozorgzadai.bookshelf.model.Book


class FakeNetworkBooksRepository : BooksRepository {
    override suspend fun getBooks(): List<Book> {
        return FakeDataSource.fakeBooksResponse.items
    }
}