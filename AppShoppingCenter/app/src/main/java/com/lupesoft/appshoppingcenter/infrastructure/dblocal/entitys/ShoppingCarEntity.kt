package com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ShoppingCart")
data class ShoppingCarEntity(
        @PrimaryKey
        @ColumnInfo(name = "idMovieIntoShopCart")
        val id: Int
)