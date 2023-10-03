package com.pthw.codemanagement.domain.coroutine

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

abstract class FlowUseCase<I, O> constructor(
  protected val dispatcherProvider: DispatcherProvider
) {

  suspend fun execute(params: I): Flow<O> {
    return provide(params)
      .flowOn(dispatcherProvider.io())
  }

  protected abstract suspend fun provide(
    params: I
  ): Flow<O>

}
