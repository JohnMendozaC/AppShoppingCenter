package com.lupesoft.appshoppingcenter.infrastructure.dblocal.repositorys

import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.domain.repositorys.MovieRepository
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.MovieDao
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.dtos.toDomainModel
import javax.inject.Inject

class MovieRepositoryRoom @Inject constructor(
        private val movieDao: MovieDao
) : MovieRepository {

    override fun getAllMovies(): List<Movie> {
        return movieDao.getAllMovies().toDomainModel()
    }
}