package com.pthw.codemanagement.di

import com.pthw.codemanagement.network.moviefeature.datasource.HomeNetworkDataSource
import com.pthw.codemanagement.network.moviefeature.datasource.HomeNetworkDataSourceImpl
import com.pthw.codemanagement.network.moviefeature.service.HomeService
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit

/**
 * Created by P.T.H.W on 02/10/2023.
 */
@Module
@InstallIn(ViewModelComponent::class)
abstract class NetworkDataSourceModule {

    @Binds
    abstract fun bindHomeNetworkDataSource(homeNetworkDataSourceImpl: HomeNetworkDataSourceImpl): HomeNetworkDataSource

    companion object {
        @Provides
        fun provideHomeService(retrofit: Retrofit): HomeService {
            return retrofit.create(HomeService::class.java)
        }
    }

}
