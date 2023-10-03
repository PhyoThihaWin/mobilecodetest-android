package com.pthw.codemanagement.data.moviefeature.repository

import com.pthw.codemanagement.cache.home.datasource.HomeCacheDataSource
import com.pthw.codemanagement.network.moviefeature.datasource.HomeNetworkDataSource
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.network.moviefeature.datasource.TYPE_POPULAR_MOVIE
import com.pthw.codemanagement.network.moviefeature.datasource.TYPE_UPCOMING_MOVIE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class HomeRepositoryImpl @Inject constructor(
    private val homeNetworkDataSource: HomeNetworkDataSource,
    private val homeCacheDataSource: HomeCacheDataSource
) : HomeRepository {

    override suspend fun fetchPopularMovies() {
        val movies = homeNetworkDataSource.getPopularMovies()
        val favoriteList = homeCacheDataSource.getFavoriteMovies(TYPE_POPULAR_MOVIE)
        movies.map {
            if (favoriteList.contains(it.id)) {
                it.isFavorite = true
            }
        }
        homeCacheDataSource.saveMovies(TYPE_POPULAR_MOVIE, movies)
    }

    override suspend fun fetchUpcomingMovies() {
        val movies = homeNetworkDataSource.getUpComingMovies()
        val favoriteList = homeCacheDataSource.getFavoriteMovies(TYPE_UPCOMING_MOVIE)
        movies.map {
            if (favoriteList.contains(it.id)) {
                it.isFavorite = true
            }
        }
        homeCacheDataSource.saveMovies(TYPE_UPCOMING_MOVIE, movies)
    }

    override fun getPopularMovies(): Flow<List<MovieDataVO>> {
        return homeCacheDataSource.getMoviesData(TYPE_POPULAR_MOVIE)
    }

    override fun getUpComingMovies(): Flow<List<MovieDataVO>> {
        return homeCacheDataSource.getMoviesData(TYPE_UPCOMING_MOVIE)
    }

    override fun getMovieDetail(id: Int): Flow<MovieDataVO> {
        return homeCacheDataSource.getMovieDetail(id)
    }

    override suspend fun updateFavorite(movieId: Int, isFavorite: Boolean) {
        homeCacheDataSource.updateFavorite(movieId, isFavorite)
    }

    override suspend fun getFavoriteMovies(movieType: String): List<Int> {
        return homeCacheDataSource.getFavoriteMovies(movieType)
    }

}