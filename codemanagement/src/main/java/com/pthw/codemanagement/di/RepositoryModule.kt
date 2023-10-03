package com.pthw.codemanagement.di

import com.pthw.codemanagement.data.moviefeature.repository.HomeRepository
import com.pthw.codemanagement.data.moviefeature.repository.HomeRepositoryImpl
import com.pthw.codemanagement.domain.coroutine.DefaultDispatcherProvider
import com.pthw.codemanagement.domain.coroutine.DispatcherProvider
import com.pthw.codemanagement.utils.exceptionhandler.ExceptionToStringMapper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import com.pthw.codemanagement.utils.exceptionhandler.ExceptionToStringMapperImpl as ExceptionToStringMapperImpl1

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepositoryModule {
    @Binds
    abstract fun exceptionToStringMapper(exceptionToStringMapperImpl: ExceptionToStringMapperImpl1): ExceptionToStringMapper

    @Binds
    abstract fun dispatcherProvider(dispatcherProvider: DefaultDispatcherProvider): DispatcherProvider

    @Binds
    abstract fun bindHomeRepository(repositoryImpl: HomeRepositoryImpl): HomeRepository

}