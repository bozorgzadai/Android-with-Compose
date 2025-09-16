package io.github.bozorgzadai.bookshelf.data

import io.github.bozorgzadai.bookshelf.model.Book
import io.github.bozorgzadai.bookshelf.network.BooksApiService


/**
 * Repository that fetch books list from marsApi.
 */
interface BooksRepository {
    /** Fetches list of Book from booksApi */
    suspend fun getBooks(): List<Book>
}

/**
 * Network Implementation of Repository that fetch books list from booksApiService.
 */
class NetworkBooksRepository(
    private val booksApiService: BooksApiService
) : BooksRepository {
    /** Fetches list of Book from booksApi */
    override suspend fun getBooks(): List<Book> {
        val response = booksApiService.getBooks()
        for (book in response.items) {
            book.volumeInfo.imageLinks.thumbnail = book.volumeInfo.imageLinks.thumbnail.replace("http", "https")
        }

        return response.items
    }
}