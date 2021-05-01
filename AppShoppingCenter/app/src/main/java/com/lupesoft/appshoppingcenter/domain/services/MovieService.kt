package com.lupesoft.appshoppingcenter.domain.services

import com.lupesoft.appshoppingcenter.domain.repositorys.MovieRepository
import javax.inject.Inject

class MovieService @Inject constructor(private val movieRepository: MovieRepository) {
}