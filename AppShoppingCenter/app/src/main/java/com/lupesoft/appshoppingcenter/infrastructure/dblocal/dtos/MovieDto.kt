package com.lupesoft.appshoppingcenter.infrastructure.dblocal.dtos

import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity


fun List<Movie>.toMovieEntity(): List<MovieEntity> {
    return map {
        MovieEntity(
                it.id,
                it.adult,
                it.backdropPath,
                it.mediaType,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount)
    }
}

fun List<MovieEntity>.toDomainModel(): List<Movie> {
    return map {
        Movie(
                it.adult,
                it.backdropPath,
                it.id,
                it.mediaType,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.title,
                it.video,
                it.voteAverage,
                it.voteCount)
    }
}