package com.lupesoft.appshoppingcenter.application.dimodule

import android.content.Context
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.AppDataBase
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.MovieDao
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.ShoppingCartDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDataBase =
            AppDataBase.getInstance(context)

    @Provides
    fun provideMovieDao(appDataBase: AppDataBase): MovieDao = appDataBase.movieDao()

    @Provides
    fun provideShoppingCartDao(appDataBase: AppDataBase): ShoppingCartDao = appDataBase.shoppingCartDao()
}