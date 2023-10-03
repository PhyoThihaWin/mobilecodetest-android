package com.pthw.codemanagement.cache.home.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by P.T.H.W on 02/10/2023.
 */

@Entity(tableName = "movie")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true) val tableId: Long = 0L,
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
    val isFavorite: Boolean = false
)
