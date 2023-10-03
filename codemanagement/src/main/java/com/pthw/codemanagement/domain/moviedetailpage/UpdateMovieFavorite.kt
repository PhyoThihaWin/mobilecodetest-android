package com.pthw.codemanagement.domain.moviedetailpage

import com.pthw.codemanagement.data.moviefeature.repository.HomeRepository
import com.pthw.codemanagement.domain.coroutine.CoroutineUseCase
import com.pthw.codemanagement.domain.coroutine.DispatcherProvider
import com.pthw.codemanagement.domain.coroutine.TwoParams
import javax.inject.Inject

/**
 * Created by P.T.H.W on 03/10/2023.
 */
class UpdateMovieFavorite @Inject constructor(
    dispatcherProvider: DispatcherProvider,
    private val repository: HomeRepository
) : CoroutineUseCase<TwoParams<Int, Boolean>, Unit>(dispatcherProvider) {
    override suspend fun provide(params: TwoParams<Int, Boolean>) {
        repository.updateFavorite(params.one, params.two)
    }
}