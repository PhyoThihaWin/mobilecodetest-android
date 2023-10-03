package com.pthw.codemanagement.cache.home.mapper

import com.pthw.codemanagement.cache.home.entity.MovieEntity
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.network.moviefeature.datasource.TYPE_POPULAR_MOVIE
import com.pthw.sharebase.core.mapper.UnidirectionalMap
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class MovieVOMapper @Inject constructor() : UnidirectionalMap<MovieEntity, MovieDataVO> {
    override fun map(item: MovieEntity): MovieDataVO {
        return MovieDataVO(
            adult = item.adult,
            backdropPath = item.backdropPath,
            genreIds = item.genreIds,
            id = item.id,
            originalLanguage = item.originalLanguage,
            originalTitle = item.originalTitle,
            overview = item.overview,
            popularity = item.popularity,
            posterPath = item.posterPath,
            releaseDate = item.releaseDate,
            title = item.title,
            video = item.video,
            voteAverage = item.voteAverage,
            voteCount = item.voteCount,
            movieType = TYPE_POPULAR_MOVIE,
            isFavorite = item.isFavorite
        )
    }
}