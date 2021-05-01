package com.lupesoft.appshoppingcenter.infrastructure.api.daos

import com.lupesoft.appshoppingcenter.infrastructure.api.vos.MoviesVo
import retrofit2.Response
import retrofit2.http.GET

interface MovieDaoRetrofit {
    @GET("list/3?api_key=48fa59288a9ab4db2d50e529f63fbd27&language=en-US")
    suspend fun getAllMovies(): Response<MoviesVo>
}