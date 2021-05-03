package com.lupesoft.appshoppingcenter.infrastructure.api.daos

import com.lupesoft.appshoppingcenter.BuildConfig.ApiKey
import com.lupesoft.appshoppingcenter.infrastructure.api.vos.MoviesVo
import retrofit2.Response
import retrofit2.http.GET

interface MovieDaoRetrofit {
    @GET("list/3?api_key=${ApiKey}&language=en-US")
    suspend fun getAllMovies(): Response<MoviesVo>
}