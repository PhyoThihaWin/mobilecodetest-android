package com.pthw.codemanagement.domain.moviedetailpage

import com.pthw.codemanagement.data.moviefeature.repository.HomeRepository
import com.pthw.codemanagement.data.moviefeature.vos.MovieDataVO
import com.pthw.codemanagement.domain.coroutine.CoroutineUseCase
import com.pthw.codemanagement.domain.coroutine.DispatcherProvider
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Created by P.T.H.W on 03/10/2023.
 */
class GetMovieDetail @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: HomeRepository
) : CoroutineUseCase<Int, Flow<MovieDataVO>>(dispatcherProvider) {
    override suspend fun provide(params: Int): Flow<MovieDataVO> {
        return repository.getMovieDetail(params)
    }
}