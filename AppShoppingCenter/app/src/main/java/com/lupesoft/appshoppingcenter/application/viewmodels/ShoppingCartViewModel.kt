package com.lupesoft.appshoppingcenter.application.viewmodels

import androidx.lifecycle.ViewModel
import com.lupesoft.appshoppingcenter.domain.services.ShoppingCartService
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ShoppingCartViewModel @Inject constructor(
    private val shoppingCartService: ShoppingCartService
) : ViewModel() {

    val shoppingCartMovies
        get() = shoppingCartService.getAllMoviesIntoShoppingCart()

    fun deleteMovie(idMovie: Int) = shoppingCartService.deleteMovie(idMovie)

    fun deleteAllMovie() = shoppingCartService.deleteAllMovie()
}