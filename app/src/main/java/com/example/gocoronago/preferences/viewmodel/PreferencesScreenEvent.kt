package com.example.gocoronago.preferences.viewmodel

import com.example.gocoronago.preferences.domain.constants.Language

sealed class PreferencesScreenEvent {
    object CountryDropdownClicked: PreferencesScreenEvent()
    object LanguageDropdownClicked: PreferencesScreenEvent()
    object DialogDismissed: PreferencesScreenEvent()
    object ThemeToggled: PreferencesScreenEvent()
    data class CountrySelected(val newCountry: String): PreferencesScreenEvent()
    data class LanguageSelected(val newLanguage: Language): PreferencesScreenEvent()
}