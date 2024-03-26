package com.akliars.kotlinbitirmeprojesi.di

import com.akliars.kotlinbitirmeprojesi.data.datasource.YemeklerDataSource
import com.akliars.kotlinbitirmeprojesi.data.repository.YemeklerRepository
import com.akliars.kotlinbitirmeprojesi.retrofit.ApiUtils
import com.akliars.kotlinbitirmeprojesi.retrofit.YemeklerDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideYemeklerRepository(yds:YemeklerDataSource) : YemeklerRepository{
        return YemeklerRepository(yds)
    }
    @Provides
    @Singleton
    fun provideYemeklerDataSource(ydao:YemeklerDao) : YemeklerDataSource{
        return YemeklerDataSource(ydao)
    }
    @Provides
    @Singleton
    fun provideYemeklerDao() : YemeklerDao{
        return ApiUtils.getYemeklerDao()
    }
}