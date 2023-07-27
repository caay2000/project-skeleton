package com.github.caay2000.projectskeleton.context.book.mother

import com.github.caay2000.common.test.RandomStringGenerator
import com.github.caay2000.projectskeleton.context.book.domain.Book
import com.github.caay2000.projectskeleton.context.book.domain.BookAuthor
import com.github.caay2000.projectskeleton.context.book.domain.BookAvailable
import com.github.caay2000.projectskeleton.context.book.domain.BookId
import com.github.caay2000.projectskeleton.context.book.domain.BookIsbn
import com.github.caay2000.projectskeleton.context.book.domain.BookPages
import com.github.caay2000.projectskeleton.context.book.domain.BookPublisher
import com.github.caay2000.projectskeleton.context.book.domain.BookTitle
import java.util.UUID
import kotlin.random.Random

object BookMother {

    fun random(
        id: BookId = BookIdMother.random(),
        isbn: String = UUID.randomUUID().toString(),
        title: String = RandomStringGenerator.randomString(),
        author: String = RandomStringGenerator.randomString(),
        pages: Int = Random.nextInt(20, 1000),
        publisher: String = RandomStringGenerator.randomString(),
        available: Boolean = true,
    ) = Book(
        id = id,
        isbn = BookIsbn(isbn),
        title = BookTitle(title),
        author = BookAuthor(author),
        pages = BookPages(pages),
        publisher = BookPublisher(publisher),
        available = BookAvailable(available),
    )
}