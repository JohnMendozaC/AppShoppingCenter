package com.lupesoft.appshoppingcenter.application.utilities

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity
import org.hamcrest.Matcher


val testMovies = arrayListOf(
    MovieEntity(
        id = 464052,
        adult = false,
        backdropPath = "",
        mediaType = "movie",
        originalLanguage = "en",
        originalTitle = "Wonder Woman 1984",
        overview = "A botched store robbery places Wonder Woman in a global battle against a powerful and mysterious ancient force that puts her powers in jeopardy.",
        popularity = 990.512,
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        title = "Wonder Woman 1984",
        video = false,
        voteAverage = 6.8,
        voteCount = 5002
    ),
    MovieEntity(
        id = 287947,
        adult = false,
        backdropPath = "",
        mediaType = "movie",
        originalLanguage = "en",
        originalTitle = "Shazam!",
        overview = "A boy is given the ability to become an adult superhero in times of need with a single magic word.",
        popularity = 990.512,
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        title = "Wonder Woman 1984",
        video = false,
        voteAverage = 6.8,
        voteCount = 5002
    ),
    MovieEntity(
        id = 141052,
        adult = false,
        backdropPath = "",
        mediaType = "movie",
        originalLanguage = "en",
        originalTitle = "Justice League",
        overview = "Fuelled by his restored faith in humanity and inspired by Superman's selfless act, Bruce Wayne and Diana Prince assemble a team of metahumans consisting of Barry Allen, Arthur Curry and Victor Stone to face the catastrophic threat of Steppenwolf and the Parademons who are on the hunt for three Mother Boxes on Earth.",
        popularity = 990.512,
        posterPath = "/8UlWHLMpgZm9bx6QYh0NFoq67TZ.jpg",
        title = "Wonder Woman 1984",
        video = false,
        voteAverage = 6.8,
        voteCount = 5002
    )
)

val testMovie = testMovies[0]

fun waitFor(delay: Long): ViewAction {
    return object : ViewAction {
        override fun perform(uiController: UiController?, view: View?) {
            uiController?.loopMainThreadForAtLeast(delay)
        }

        override fun getConstraints(): Matcher<View> {
            return ViewMatchers.isRoot()
        }

        override fun getDescription(): String {
            return "wait for " + delay + "milliseconds"
        }
    }
}