package com.lupesoft.appshoppingcenter.infrastructure.dblocal.dtos

import com.lupesoft.appshoppingcenter.domain.entitys.Movie
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity


fun Movie.toReceiptEntity(): MovieEntity = MovieEntity(
        this.id,
        this.adult,
        this.backdropPath,
        this.genreIds,
        this.mediaType,
        this.originalLanguage,
        this.originalTitle,
        this.overview,
        this.popularity,
        this.posterPath,
        this.release_date,
        this.originalTitle,
        this.video,
        this.voteAverage,
        this.voteCount
)


fun List<MovieEntity>.toDomainModel(): List<Movie> {
    return map {
        Movie(
                it.adult,
                it.backdropPath,
                it.genreIds,
                it.id,
                it.mediaType,
                it.originalLanguage,
                it.originalTitle,
                it.overview,
                it.popularity,
                it.posterPath,
                it.release_date,
                it.originalTitle,
                it.video,
                it.voteAverage,
                it.voteCount)
    }
}