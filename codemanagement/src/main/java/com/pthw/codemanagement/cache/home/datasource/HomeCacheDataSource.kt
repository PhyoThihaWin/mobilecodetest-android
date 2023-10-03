package com.pthw.codemanagement.cache.home.datasource

import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import kotlinx.coroutines.flow.Flow

/**
 * Created by P.T.H.W on 02/10/2023.
 */
interface HomeCacheDataSource {
    fun getMoviesData(movieType: String): Flow<List<MovieDataVO>>
    suspend fun saveMovies(movieType: String, movies: List<MovieDataVO>)

    fun getMovieDetail(id: Int): Flow<MovieDataVO>
    suspend fun updateFavorite(movieId: Int, isFavorite: Boolean)
    suspend fun getFavoriteMovies(movieType: String): List<Int>
}