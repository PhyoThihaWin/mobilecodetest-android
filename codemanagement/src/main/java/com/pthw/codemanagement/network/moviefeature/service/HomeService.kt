package com.pthw.codemanagement.network.moviefeature.service

import com.pthw.codemanagement.network.moviefeature.response.MovieResponse
import com.pthw.codemanagement.network.utils.DataResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeService {
    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") type: Int = 1
    ): Response<DataResponse<List<MovieResponse>>>

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") type: Int = 1
    ): Response<DataResponse<List<MovieResponse>>>

}