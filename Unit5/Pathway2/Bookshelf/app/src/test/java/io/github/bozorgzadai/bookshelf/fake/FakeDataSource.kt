package io.github.bozorgzadai.bookshelf.fake
import io.github.bozorgzadai.bookshelf.model.Book
import io.github.bozorgzadai.bookshelf.model.BooksResponse
import io.github.bozorgzadai.bookshelf.model.ImageLinks
import io.github.bozorgzadai.bookshelf.model.VolumeInfo
import kotlinx.serialization.Serializable

object FakeDataSource {
    private const val idOne = "book1"
    private const val idTwo = "book2"
    private const val thumbnailOne = "https://example.com/thumb1.jpg"
    private const val thumbnailTwo = "https://example.com/thumb2.jpg"

    val fakeBooksResponse = BooksResponse(
        items = listOf(
            Book(
                id = idOne,
                volumeInfo = VolumeInfo(
                    imageLinks = ImageLinks(
                        thumbnail = thumbnailOne
                    )
                )
            ),
            Book(
                id = idTwo,
                volumeInfo = VolumeInfo(
                    imageLinks = ImageLinks(
                        thumbnail = thumbnailTwo
                    )
                )
            )
        )
    )
}