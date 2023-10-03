package com.pthw.codemanagement.cache.home.datasource

import androidx.room.withTransaction
import com.pthw.codemanagement.cache.database.AppDatabase
import com.pthw.codemanagement.cache.home.mapper.MovieEntityMapper
import com.pthw.codemanagement.cache.home.mapper.MovieVOMapper
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class HomeCacheDataSourceImpl @Inject constructor(
    private val database: AppDatabase,
    private val movieVOMapper: MovieVOMapper,
    private val movieEntityMapper: MovieEntityMapper
) : HomeCacheDataSource {
    override fun getMoviesData(movieType: String): Flow<List<MovieDataVO>> {
        return database.homeMoviesDao().getMovies(movieType).map {
            it.map(movieVOMapper::map)
        }
    }

    override suspend fun saveMovies(movieType: String, movies: List<MovieDataVO>) {
       database.withTransaction {
           database.homeMoviesDao().clearMovies(movieType)
           database.homeMoviesDao().insertMovies(movies.map(movieEntityMapper::map))
       }
    }

    override fun getMovieDetail(id: Int): Flow<MovieDataVO> {
        return database.homeMoviesDao().getMovieDetail(id).map(movieVOMapper::map)
    }

    override suspend fun updateFavorite(movieId: Int, isFavorite: Boolean) {
        database.homeMoviesDao().updateFavorite(movieId, isFavorite)
    }

    override suspend fun getFavoriteMovies(movieType: String): List<Int> {
        return database.homeMoviesDao().getFavoriteMovies(movieType)
    }

}