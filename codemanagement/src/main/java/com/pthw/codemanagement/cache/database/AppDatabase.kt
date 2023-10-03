package com.pthw.codemanagement.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pthw.codemanagement.cache.home.dao.HomeMoviesDao
import com.pthw.codemanagement.cache.home.entity.MovieEntity
import com.pthw.codemanagement.cache.home.typeconverter.GenresTypeConverter


@Database(
    entities = [
        MovieEntity::class
    ],
    version = 2,
    exportSchema = false
)

@TypeConverters(
    GenresTypeConverter::class,
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun homeMoviesDao(): HomeMoviesDao
}