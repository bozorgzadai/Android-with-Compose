package io.github.bozorgzadai.bookshelf.network

import io.github.bozorgzadai.bookshelf.model.BooksResponse
import retrofit2.http.GET


/**
 * A public interface that exposes the [getBooks] method
 */
interface BooksApiService {
    /**
     * Returns a [BooksResponse] and this method can be called from a Coroutine.
     * The @GET annotation indicates that the "books" endpoint will be requested with the GET
     * HTTP method
     */
    @GET("books/v1/volumes?q=jazz+history")
    suspend fun getBooks(): BooksResponse
}

