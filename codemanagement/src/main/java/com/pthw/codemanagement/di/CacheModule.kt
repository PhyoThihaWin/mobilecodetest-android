package com.pthw.codemanagement.di

import android.content.Context
import androidx.room.Room
import com.pthw.codemanagement.cache.database.AppDatabase
import com.pthw.codemanagement.cache.home.datasource.HomeCacheDataSourceImpl
import com.pthw.codemanagement.cache.home.datasource.HomeCacheDataSource
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
abstract class CacheModule {
    @Binds
    abstract fun bindHomeCacheDataSource(homeCacheDataSourceImpl: HomeCacheDataSourceImpl): HomeCacheDataSource
}

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "tmdb_movie.db")
            .fallbackToDestructiveMigration()
            .build()
    }

}