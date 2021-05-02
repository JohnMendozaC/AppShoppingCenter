package com.lupesoft.appshoppingcenter.application.viewmodels

import androidx.lifecycle.ViewModel
import com.lupesoft.appshoppingcenter.domain.services.MovieService
import com.lupesoft.appshoppingcenter.domain.services.ShoppingCartService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class MoviesViewModel @Inject constructor(
    movieService: MovieService,
    private val shoppingCartService: ShoppingCartService
) : ViewModel() {

    val allMovies = movieService.getAllMovies()

    fun addMovie(idMovie: Int) = shoppingCartService.addMovie(idMovie)

    fun deleteMovie(idMovie: Int) = shoppingCartService.deleteMovie(idMovie)
}