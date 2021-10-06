package com.example.gocoronago.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.gocoronago.about.repository.AboutUsRepository
import com.example.gocoronago.about.repository.IAboutUsRepository
import com.example.gocoronago.about.usecase.GetContributorListUseCase
import com.example.gocoronago.about.usecase.GetVersionNameUseCase
import com.example.gocoronago.about.usecase.IGetContributorListUseCase
import com.example.gocoronago.about.usecase.IGetVersionNameUseCase
import com.example.gocoronago.base.IDispatcherProvider
import com.example.gocoronago.base.ProductionDispatcherProvider
import com.example.gocoronago.preferences.data.repository.PreferencesRepositoryImpl
import com.example.gocoronago.preferences.data.repository.preferencesDataStore
import com.example.gocoronago.preferences.domain.repository.IPreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
        fun provideGetContributorListUseCase(usecase: GetContributorListUseCase): IGetContributorListUseCase

        @Binds
        @Singleton
        fun provideGetVersionNameUseCase(usecase: GetVersionNameUseCase): IGetVersionNameUseCase

        @Binds
        @Singleton
        fun provideAboutUsRepository(repo: AboutUsRepository): IAboutUsRepository

    }

    @Provides
    @Singleton
    fun provideProductionDispatcherProvider(): IDispatcherProvider = ProductionDispatcherProvider

    @Provides
    @Singleton
    fun providePreferencesDataStore(
        @ApplicationContext context: Context
    ): DataStore<Preferences> = context.preferencesDataStore

    @Provides
    @Singleton
    fun providePreferencesRepository(
        preferencesDataStore: DataStore<Preferences>,
        dispatcherProvider: IDispatcherProvider
    ): IPreferencesRepository = PreferencesRepositoryImpl(
        preferencesDataStore = preferencesDataStore,
        dispatcherProvider = dispatcherProvider
    )

}