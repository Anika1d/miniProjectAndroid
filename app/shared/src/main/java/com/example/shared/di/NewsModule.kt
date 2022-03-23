package com.example.shared.di

import com.example.shared.repository.Repository
import com.example.shared.retrofit.RetrofitServices
import com.example.shared.retrofit.repository.RepositoryApiImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
interface NewsModule {
    @Binds
    fun bindRepository(repository: RepositoryApiImpl): Repository
}


@Module
@InstallIn(SingletonComponent::class)
object NewsApiModule {
    @Singleton
    @Provides
    fun provideApiModule(retrofit: Retrofit): RetrofitServices =
        retrofit.create(RetrofitServices::class.java)
}