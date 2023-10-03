package com.pthw.codemanagement.network.moviefeature.datasource

import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO

/**
 * Created by P.T.H.W on 02/10/2023.
 */
interface HomeNetworkDataSource {
    suspend fun getPopularMovies(): List<MovieDataVO>
    suspend fun getUpComingMovies(): List<MovieDataVO>
}