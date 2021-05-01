package com.lupesoft.appshoppingcenter.domain.aggregates

import com.lupesoft.appshoppingcenter.domain.entitys.Movie

class Movies(createdBy: String,
             description: String,
             favoriteCount: Int,
             id: String,
             items: List<Movie>,
             itemCount: Int,
             iso_639_1: String,
             name: String,
             posterPath: String) {

    var createdBy: String = createdBy
        private set
    var description: String = description
        private set
    var favoriteCount: Int = favoriteCount
        private set
    var id: String = id
        private set
    var items: List<Movie> = items
        private set
    var itemCount: Int = itemCount
        private set
    var iso_639_1: String = iso_639_1
        private set
    var name: String = name
        private set
    var posterPath: String = posterPath
        private set
}