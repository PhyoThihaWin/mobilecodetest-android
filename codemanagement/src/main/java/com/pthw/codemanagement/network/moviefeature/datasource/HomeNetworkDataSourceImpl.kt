package com.pthw.codemanagement.network.moviefeature.datasource

import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.network.extension.getBody
import com.pthw.codemanagement.network.moviefeature.mapper.PopularMovieNetworkMapper
import com.pthw.codemanagement.network.moviefeature.mapper.UpComingMovieNetworkMapper
import com.pthw.codemanagement.network.moviefeature.service.HomeService
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */

const val TYPE_POPULAR_MOVIE = "popular_movie"
const val TYPE_UPCOMING_MOVIE = "upcoming_movie"

class HomeNetworkDataSourceImpl @Inject constructor(
    private val service: HomeService,
    private val popularMovieNetworkMapper: PopularMovieNetworkMapper,
    private val upComingMovieNetworkMapper: UpComingMovieNetworkMapper
) : HomeNetworkDataSource {
    override suspend fun getPopularMovies(): List<MovieDataVO> {
        val raw = service.getPopularMovies().getBody()
        return raw.data?.map(popularMovieNetworkMapper::map).orEmpty()
    }

    override suspend fun getUpComingMovies(): List<MovieDataVO> {
        val raw = service.getUpComingMovies().getBody()
        return raw.data?.map(upComingMovieNetworkMapper::map).orEmpty()
    }
}