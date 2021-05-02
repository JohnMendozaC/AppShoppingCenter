package com.lupesoft.appshoppingcenter.domain.entitys

class Movie(adult: Boolean,
            backdropPath: String,
            id: Int,
            mediaType: String,
            originalLanguage: String,
            originalTitle: String,
            overview: String,
            popularity: Double,
            posterPath: String,
            title: String,
            video: Boolean,
            voteAverage: Double,
            voteCount: Int) {

    var adult: Boolean = adult
        private set
    var backdropPath: String = backdropPath
        private set
    var id: Int = id
        private set
    var mediaType: String = mediaType
        private set
    var originalLanguage: String = originalLanguage
        private set
    var originalTitle: String = originalTitle
        private set
    var overview: String = overview
        private set
    var popularity: Double = popularity
        private set
    var posterPath: String = posterPath
        private set
    var title: String = title
        private set
    var video: Boolean = video
        private set
    var voteAverage: Double = voteAverage
        private set
    var voteCount: Int = voteCount
        private set
}