package io.github.bozorgzadai.bookshelf.model

import kotlinx.serialization.Serializable


@Serializable
data class BooksResponse(
    val items: List<Book>
)


@Serializable
data class Book(
    val id: String,
    val volumeInfo: VolumeInfo
)

@Serializable
data class VolumeInfo(
    val imageLinks: ImageLinks
)

@Serializable
data class ImageLinks(
    var thumbnail: String
)
