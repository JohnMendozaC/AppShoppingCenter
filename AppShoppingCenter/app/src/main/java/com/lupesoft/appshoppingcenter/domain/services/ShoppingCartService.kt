package com.lupesoft.appshoppingcenter.domain.services

import android.content.Context
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.lupesoft.appshoppingcenter.R
import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.domain.repositorys.ShoppingCartRepository
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.utils.response.Resource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class ShoppingCartService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val shoppingCartRepository: ShoppingCartRepository
) {

    fun getAllMoviesIntoShoppingCart(): LiveData<Resource<List<Movie>>> {
        val flow = flow {
            emit(
                Resource.success(data = shoppingCartRepository.getAllMoviesIntoShoppingCart())
            )
        }
        return flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch {
                emit(
                    Resource.error(
                        null,
                        0,
                        context.getString(R.string.something_unexpected_happened)
                    )
                )
            }
            .flowOn(Dispatchers.IO)
            .asLiveData()
    }

    fun addMovie(idMovie: Int): LiveData<Resource<Long>> {
        val flow = flow {
            emit(
                Resource.success(
                    shoppingCartRepository.addMovie(idMovie),
                    message = context.getString(R.string.add_successfully)
                )
            )
        }
        return flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch { exception ->
                with(exception) {
                    val msg = when (this) {
                        is SQLiteConstraintException -> context.getString(R.string.movie_is_already)
                        else -> context.getString(R.string.something_unexpected_happened)
                    }
                    emit(Resource.error(null, 0, msg))
                }
            }
            .flowOn(Dispatchers.IO)
            .asLiveData()
    }

    fun deleteMovie(idMovie: Int): LiveData<Resource<Int>> {
        val flow = flow {
            emit(
                Resource.success(
                    shoppingCartRepository.deleteMovie(idMovie),
                    message = context.getString(R.string.remove_successfully)
                )
            )
        }
        return flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch {
                emit(
                    Resource.error(
                        null,
                        0,
                        context.getString(R.string.something_unexpected_happened)
                    )
                )
            }
            .flowOn(Dispatchers.IO)
            .asLiveData()
    }

    fun deleteAllMovie(): LiveData<Resource<Int>> {
        val flow = flow {
            emit(
                Resource.success(
                    shoppingCartRepository.deleteAllMovie(),
                    message = context.getString(R.string.remove_all_successfully)
                )
            )
        }
        return flow
            .onStart { emit(Resource.loading(null, null)) }
            .catch {
                emit(
                    Resource.error(
                        null,
                        0,
                        context.getString(R.string.something_unexpected_happened)
                    )
                )
            }
            .flowOn(Dispatchers.IO)
            .asLiveData()
    }
}