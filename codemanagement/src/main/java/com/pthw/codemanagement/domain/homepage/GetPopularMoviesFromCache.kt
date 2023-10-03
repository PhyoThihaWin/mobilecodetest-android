package com.pthw.codemanagement.domain.homepage

import com.pthw.codemanagement.data.moviefeature.repository.HomeRepository
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.domain.coroutine.DispatcherProvider
import com.pthw.codemanagement.domain.coroutine.FlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class GetPopularMoviesFromCache @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: HomeRepository
) : FlowUseCase<Unit, List<MovieDataVO>>(dispatcherProvider) {
    override suspend fun provide(params: Unit): Flow<List<MovieDataVO>> {
        return repository.getPopularMovies()
    }
}