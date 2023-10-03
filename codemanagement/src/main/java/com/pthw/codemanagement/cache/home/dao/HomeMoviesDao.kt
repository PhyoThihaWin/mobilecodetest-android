package com.pthw.codemanagement.cache.home.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pthw.codemanagement.cache.home.entity.MovieEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface HomeMoviesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovies(repos: List<MovieEntity>)

    @Query("SELECT * FROM movie WHERE movieType=:movieType")
    fun getMovies(movieType: String): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movie WHERE id=:movieId")
    fun getMovieDetail(movieId: Int): Flow<MovieEntity>

    @Query("UPDATE movie SET isFavorite=:isFavorite WHERE id = :movieId")
    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean)

    @Query("SELECT id FROM movie WHERE movieType=:movieType And isFavorite=:isFavorite")
    fun getFavoriteMovies(movieType: String, isFavorite: Boolean = true): List<Int>

    @Query("DELETE FROM movie WHERE movieType=:movieType")
    suspend fun clearMovies(movieType: String)


}