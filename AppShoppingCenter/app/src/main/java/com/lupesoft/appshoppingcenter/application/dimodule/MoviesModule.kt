package com.lupesoft.appshoppingcenter.application.dimodule

import com.lupesoft.appshoppingcenter.domain.repositorys.MovieRepository
import com.lupesoft.appshoppingcenter.domain.repositorys.ShoppingCartRepository
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys.MovieRepositoryRoom
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys.ShoppingCartRepositoryRoom
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class MoviesModule {

    @Binds
    abstract fun provideMovieRepository(movieRepositoryRoom: MovieRepositoryRoom): MovieRepository

    @Binds
    abstract fun provideShoppingCartRepository(shoppingCartRepositoryRoom: ShoppingCartRepositoryRoom): ShoppingCartRepository
}