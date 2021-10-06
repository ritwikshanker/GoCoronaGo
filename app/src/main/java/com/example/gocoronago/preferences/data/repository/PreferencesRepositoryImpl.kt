package com.example.gocoronago.preferences.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.gocoronago.base.IDispatcherProvider
import com.example.gocoronago.preferences.domain.constants.ColorTheme
import com.example.gocoronago.preferences.domain.constants.Language
import com.example.gocoronago.preferences.domain.entity.UserPreferences
import com.example.gocoronago.preferences.domain.entity.defaultUserPreferences
import com.example.gocoronago.preferences.domain.repository.IPreferencesRepository
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

val Context.preferencesDataStore: DataStore<Preferences>
        by preferencesDataStore(name = "user_prefs")

class PreferencesRepositoryImpl(
    private val preferencesDataStore: DataStore<Preferences>,
    private val dispatcherProvider: IDispatcherProvider
) : IPreferencesRepository {

    override val userPreferencesFlow = preferencesDataStore
        .data
        .map { prefs ->
            val language: Language = prefs[PreferencesKeys.LANGUAGE]?.let { Language.valueOf(it) }
                ?: defaultUserPreferences.language
            val country: String = prefs[PreferencesKeys.COUNTRY] ?: defaultUserPreferences.country
            val theme: ColorTheme = prefs[PreferencesKeys.THEME]?.let { ColorTheme.valueOf(it) }
                ?: defaultUserPreferences.theme

            UserPreferences(country, theme, language)
        }
        .flowOn(dispatcherProvider.io)

    override suspend fun updateLanguage(language: Language) {
        withContext(dispatcherProvider.io) {
            preferencesDataStore.edit { preferences ->
                preferences[PreferencesKeys.LANGUAGE] = language.name
            }
        }
    }

    override suspend fun updateCountry(country: String) {
        withContext(dispatcherProvider.io) {
            preferencesDataStore.edit { preferences ->
                preferences[PreferencesKeys.COUNTRY] = country
            }
        }
    }

    override suspend fun updateTheme(theme: ColorTheme) {
        withContext(dispatcherProvider.io) {
            preferencesDataStore.edit { preferences ->
                preferences[PreferencesKeys.THEME] = theme.name
            }
        }
    }

    private object PreferencesKeys {
        val LANGUAGE = stringPreferencesKey("language")
        val COUNTRY = stringPreferencesKey("country")
        val THEME = stringPreferencesKey("theme")
    }
}