package com.lupesoft.appshoppingcenter.domain.services

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.domain.repositorys.MovieRepository
import com.lupesoft.appshoppingcenter.infrastructure.api.daos.MovieDaoRetrofit
import com.lupesoft.appshoppingcenter.infrastructure.api.dtos.toMovie
import com.lupesoft.appshoppingcenter.infrastructure.api.dtos.toMovieEntity
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.response.Resource
import com.lupesoft.soccerleague.api.response.ApiEmptyResponse
import com.lupesoft.soccerleague.api.response.ApiErrorResponse
import com.lupesoft.soccerleague.api.response.ApiResponse
import com.lupesoft.soccerleague.api.response.ApiSuccessResponse
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class MovieService @Inject constructor(
        @ApplicationContext private val context: Context,
        private val movieRepository: MovieRepository,
        private val movieDaoRetrofit: MovieDaoRetrofit
) {

    private fun getAllMoviesFromNetwork(): Flow<Resource<List<Movie>>> {
        val flow = flow {
            val response = movieDaoRetrofit.getAllMovies()
            when (val apiResponse = ApiResponse.create(response)) {
                is ApiSuccessResponse -> {
                    emit(Resource.loading(null, "Saving data in database..."))
                    movieRepository.insertAll(apiResponse.body.items.toMovieEntity())
                    emit(Resource.success(apiResponse.body.items.toMovie()))
                }
                is ApiEmptyResponse -> {
                    emit(Resource.error(null, 0, "Empty response"))
                }
                is ApiErrorResponse -> {
                    emit(Resource.error(null, apiResponse.code, apiResponse.message))
                }
            }
        }

        return flow
                .onStart { emit(Resource.loading(null, "Service fetching...")) }
                .catch { exception ->
                    with(exception) {
                        emit(Resource.error(null, 0, message))
                    }
                }
                .flowOn(Dispatchers.IO)
    }

    fun getAllMovies(): LiveData<Resource<List<Movie>>> {
        val flow = flow {
            movieRepository.getAllMovies().let {
                if (it.isNotEmpty()) {
                    emit(Resource.success(it))
                } else {
                    getAllMoviesFromNetwork().collect { service ->
                        emit(service)
                    }
                }
            }
        }
        return flow
                .onStart { emit(Resource.loading(null, null)) }
                .catch {
                    emit(Resource.error(null, 0, context.getString(R.string.something_unexpected_happened)))
                }
                .flowOn(Dispatchers.IO)
                .asLiveData()
    }

}