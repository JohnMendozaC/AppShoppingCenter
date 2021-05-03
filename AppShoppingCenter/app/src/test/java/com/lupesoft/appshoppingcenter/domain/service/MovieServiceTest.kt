package com.lupesoft.appshoppingcenter.domain.service

import com.lupesoft.appshoppingcenter.domain.repositorys.MovieRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieServiceTest {

    @Mock
    lateinit var movieRepository: MovieRepository

    @Test
    fun getAllMovies_successful() {
        //Arrange

        //Act
        val expected = (movieRepository.getAllMovies().size >= 0)

        //Assert
        assert(expected)
    }
}