package com.pthw.codemanagement.domain.homepage

import com.pthw.codemanagement.data.moviefeature.repository.HomeRepository
import com.pthw.codemanagement.domain.coroutine.CoroutineUseCase
import com.pthw.codemanagement.domain.coroutine.DispatcherProvider
import javax.inject.Inject

/**
 * Created by P.T.H.W on 02/10/2023.
 */
class FetchUpcomingMovies @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: HomeRepository
) : CoroutineUseCase<Unit, Unit>(dispatcherProvider) {
    override suspend fun provide(params: Unit) {
        repository.fetchUpcomingMovies()
    }
}