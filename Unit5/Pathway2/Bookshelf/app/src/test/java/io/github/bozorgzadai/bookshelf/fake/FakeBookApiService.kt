package io.github.bozorgzadai.bookshelf.fake

import io.github.bozorgzadai.bookshelf.model.BooksResponse
import io.github.bozorgzadai.bookshelf.network.BooksApiService


class FakeBookApiService : BooksApiService {
    override suspend fun getBooks(): BooksResponse {
        return FakeDataSource.fakeBooksResponse
    }
}