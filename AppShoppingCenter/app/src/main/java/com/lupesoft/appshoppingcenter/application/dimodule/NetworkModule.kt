package com.lupesoft.appshoppingcenter.application.dimodule

import com.lupesoft.appshoppingcenter.infrastructure.api.Api
import com.lupesoft.appshoppingcenter.infrastructure.api.daos.MovieDaoRetrofit
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideMovieDaoRetrofit(): MovieDaoRetrofit = Api.create()
}