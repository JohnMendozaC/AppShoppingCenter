package com.lupesoft.appshoppingcenter.infrastructure.dblocal

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.infrastructure.dblocal.utils.DATABASE_NAME
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.MovieDao
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.daos.ShoppingCartDao
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.MovieEntity
import com.lupesoft.appshoppingcenter.infrastructure.dblocal.entitys.ShoppingCarEntity

@Database(
        entities = [MovieEntity::class
            , ShoppingCarEntity::class],
        version = 1,
        exportSchema = false
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
    abstract fun shoppingCartDao(): ShoppingCartDao

    companion object {

        @Volatile
        private var instance: AppDataBase? = null

        fun getInstance(context: Context): AppDataBase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDataBase {
            return Room.databaseBuilder(context, AppDataBase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}