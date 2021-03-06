package com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.ShoppingCarEntity

@Dao
interface ShoppingCartDao {

    @Insert
    fun addMovie(shoppingCartEntity: ShoppingCarEntity): Long

    @Query("DELETE FROM ShoppingCart WHERE idMovieIntoShopCart = :id")
    fun deleteMovie(id: Int): Int

    @Query("DELETE FROM ShoppingCart")
    fun deleteAllMoviesIntoShoppingCart(): Int

    @Query("SELECT Movie.* FROM ShoppingCart LEFT JOIN Movie ON Movie.idMovie = ShoppingCart.idMovieIntoShopCart")
    fun getAllMoviesIntoShoppingCart(): List<MovieEntity>
}