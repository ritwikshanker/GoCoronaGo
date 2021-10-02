package com.example.gocoronago.di

import com.example.gocoronago.about.repository.AboutUsRepository
import com.example.gocoronago.about.repository.IAboutUsRepository
import com.example.gocoronago.about.usecase.GetContributorListUseCase
import com.example.gocoronago.about.usecase.GetVersionNameUseCase
import com.example.gocoronago.about.usecase.IGetContributorListUseCase
import com.example.gocoronago.about.usecase.IGetVersionNameUseCase
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {


    @Module
    @InstallIn(SingletonComponent::class)
    interface InnerAppModule {

        @Binds
        @Singleton
        fun provideGetContributorListUseCase(usecase: GetContributorListUseCase) :IGetContributorListUseCase

        @Binds
        @Singleton
        fun provideGetVersionNameUseCase(usecase: GetVersionNameUseCase) :IGetVersionNameUseCase

        @Binds
        @Singleton
        fun provideAboutUsRepository(repo: AboutUsRepository) :IAboutUsRepository

    }

}