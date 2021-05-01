package com.lupesoft.appshoppingcenter.domain.aggregates

import com.lupesoft.appshoppingcenter.domain.entitys.Movie

class ShoppingCart(
        items: List<Movie>
) {

    var items: List<Movie> = items
        private set
}