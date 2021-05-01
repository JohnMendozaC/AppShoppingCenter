package com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys

import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.domain.repositorys.ShoppingCartRepository
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.ShoppingCartDao
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.dtos.toDomainModel
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.ShoppingCarEntity
import javax.inject.Inject

class ShoppingCartRepositoryRoom @Inject constructor(
        private val shoppingCartDao: ShoppingCartDao
) : ShoppingCartRepository {

    override fun getAllMoviesIntoShoppingCart(): List<Movie> {
        return shoppingCartDao.getAllMoviesIntoShoppingCart().toDomainModel()
    }

    override fun addMovie(idMovie: Int): Long {
        return shoppingCartDao.addMovie(ShoppingCarEntity(idMovie))
    }

    override fun deleteMovie(idMovie: Int): Int {
        return shoppingCartDao.deleteMovie(idMovie)
    }

    override fun deleteAllMovie(): Int {
        return shoppingCartDao.deleteAllMoviesIntoShoppingCart()
    }
}