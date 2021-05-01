package com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos

import androidx.room.Dao
import androidx.room.Query
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity

@Dao
interface MovieDao {
    @Query("SELECT * FROM Movie")
    fun getAllMovies(): List<MovieEntity>
}