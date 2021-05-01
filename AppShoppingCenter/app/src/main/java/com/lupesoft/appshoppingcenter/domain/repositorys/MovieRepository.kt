package com.lupesoft.appshoppingcenter.domain.repositorys

import com.lupesoft.appshoppingcenter.domain.entitys.Movie

interface MovieRepository {

    fun getAllMovies(): List<Movie>
}