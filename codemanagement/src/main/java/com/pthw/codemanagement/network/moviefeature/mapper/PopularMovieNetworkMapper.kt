package com.pthw.codemanagement.network.moviefeature.mapper

import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.network.moviefeature.datasource.TYPE_POPULAR_MOVIE
import com.pthw.codemanagement.network.moviefeature.response.MovieResponse
import com.pthw.sharebase.core.mapper.UnidirectionalMap
import com.pthw.sharebase.extensions.orFalse
import com.pthw.sharebase.extensions.orZero
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class PopularMovieNetworkMapper @Inject constructor() :
    UnidirectionalMap<MovieResponse?, MovieDataVO> {
    override fun map(item: MovieResponse?): MovieDataVO {
        return MovieDataVO(
            adult = item?.adult.orFalse(),
            backdropPath = item?.backdropPath.orEmpty(),
            genreIds = item?.genreIds?.map { it.orZero() }.orEmpty(),
            id = item?.id.orZero(),
            originalLanguage = item?.originalLanguage.orEmpty(),
            originalTitle = item?.originalTitle.orEmpty(),
            overview = item?.overview.orEmpty(),
            popularity = item?.popularity.orZero(),
            posterPath = item?.posterPath.orEmpty(),
            releaseDate = item?.releaseDate.orEmpty(),
            title = item?.title.orEmpty(),
            video = item?.video.orFalse(),
            voteAverage = item?.voteAverage.orZero(),
            voteCount = item?.voteCount.orZero(),
            movieType = TYPE_POPULAR_MOVIE,
        )
    }
}