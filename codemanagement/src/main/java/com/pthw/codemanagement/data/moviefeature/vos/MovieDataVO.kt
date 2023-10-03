package com.pthw.codemanagement.data.moviefeature.vos

/**
 * Created by P.T.H.W on 02/10/2023.
 */
data class MovieDataVO(
    val adult: Boolean,
    val backdropPath: String,
    val genreIds: List<Int>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val releaseDate: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,

    val movieType: String,
    var isFavorite: Boolean = false
)
