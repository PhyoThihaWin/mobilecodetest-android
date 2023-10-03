package com.pthw.codemanagement.data.moviefeature.repository

import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import kotlinx.coroutines.flow.Flow

/**
 * Created by P.T.H.W on 02/10/2023.
 */
interface HomeRepository {
    suspend fun fetchPopularMovies()
    suspend fun fetchUpcomingMovies()

    fun getPopularMovies(): Flow<List<MovieDataVO>>
    fun getUpComingMovies(): Flow<List<MovieDataVO>>

    fun getMovieDetail(id: Int): Flow<MovieDataVO>
    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean)
    suspend fun getFavoriteMovies(movieType: String): List<Int>
}