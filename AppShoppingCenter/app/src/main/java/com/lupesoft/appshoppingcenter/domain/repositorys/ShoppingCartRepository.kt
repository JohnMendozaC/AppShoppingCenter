package com.lupesoft.appshoppingcenter.domain.repositorys

import com.lupesoft.appshoppingcenter.domain.entitys.Movie

interface ShoppingCartRepository {

    fun getAllMoviesIntoShoppingCart(): List<Movie>

    fun addMovie(idMovie: Int): Long

    fun deleteMovie(idMovie: Int): Int

    fun deleteAllMovie(): Int
}