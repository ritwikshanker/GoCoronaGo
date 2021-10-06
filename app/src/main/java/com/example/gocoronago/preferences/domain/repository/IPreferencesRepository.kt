package com.example.gocoronago.preferences.domain.repository

import com.example.gocoronago.preferences.domain.constants.ColorTheme
import com.example.gocoronago.preferences.domain.constants.Language
import com.example.gocoronago.preferences.domain.entity.UserPreferences
import kotlinx.coroutines.flow.Flow

interface IPreferencesRepository {

    val userPreferencesFlow: Flow<UserPreferences>

    suspend fun updateLanguage(language: Language)

    suspend fun updateCountry(country: String)

    suspend fun updateTheme(theme: ColorTheme)

}