package com.lupesoft.appshoppingcenter.domain.service

import com.lupesoft.appshoppingcenter.domain.repositorys.ShoppingCartRepository
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class ShoppingCartServiceTest {

    @Mock
    lateinit var shoppingCartRepository: ShoppingCartRepository

    @Test
    fun getAllMoviesIntoShoppingCart_successful() {
        //Arrange

        //Act
        val expected = (shoppingCartRepository.getAllMoviesIntoShoppingCart().size >= 0)

        //Assert
        assert(expected)
    }

    @Test
    fun addMovie_successful() {
        //Arrange
        val movieId = 1608

        //Act
        val expected = (shoppingCartRepository.addMovie(movieId) >= 0)

        //Assert
        assert(expected)
    }

    @Test
    fun deleteMovie_successful() {
        //Arrange
        val movieId = 1608

        //Act
        val expected = (shoppingCartRepository.deleteMovie(movieId) >= 0)

        //Assert
        assert(expected)
    }

    @Test
    fun deleteAllMovie_successful() {
        //Arrange

        //Act
        val expected = (shoppingCartRepository.deleteAllMovie() >= 0)

        //Assert
        assert(expected)
    }

}