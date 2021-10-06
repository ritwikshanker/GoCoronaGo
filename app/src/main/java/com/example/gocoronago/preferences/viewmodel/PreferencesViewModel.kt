package com.example.gocoronago.preferences.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocoronago.preferences.domain.constants.ColorTheme
import com.example.gocoronago.preferences.domain.constants.Language
import com.example.gocoronago.preferences.domain.entity.defaultUserPreferences
import com.example.gocoronago.preferences.domain.repository.IPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PreferencesViewModel
@Inject
constructor(
    private val repository: IPreferencesRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.userPreferencesFlow.collect { prefs ->
                _selectedCountry.emit(prefs.country)
                _selectedLanguage.emit(prefs.language)
                _isLightThemeSelected.emit(prefs.theme == ColorTheme.LIGHT)
            }

            // TODO: subscribe to Country API and update [_countryList]
        }
    }

    fun onEvent(event: PreferencesScreenEvent) {
        viewModelScope.launch {
            when (event) {
                PreferencesScreenEvent.CountryDropdownClicked -> handleCountryDropdownClicked()
                is PreferencesScreenEvent.CountrySelected -> handleCountrySelected(newCountry = event.newCountry)
                PreferencesScreenEvent.DialogDismissed -> handleDialogDismissed()
                PreferencesScreenEvent.LanguageDropdownClicked -> handleLanguageDropdownClicked()
                is PreferencesScreenEvent.LanguageSelected -> handleLanguageSelected(newLanguage = event.newLanguage)
                PreferencesScreenEvent.ThemeToggled -> handleThemeToggled()
            }
        }
    }

    private suspend fun handleThemeToggled() {
        withLoading {
            val newTheme = if (_isLightThemeSelected.value)
                ColorTheme.DARK
            else
                ColorTheme.LIGHT
            repository.updateTheme(newTheme)
        }
    }

    private suspend fun handleLanguageSelected(newLanguage: Language) {
        withLoading {
            repository.updateLanguage(newLanguage)
        }
        resetDialogs()
    }

    private suspend fun handleLanguageDropdownClicked() {
        resetDialogs()
        _languageDialogVisible.emit(true)
    }

    private suspend fun handleDialogDismissed() {
        resetDialogs()
    }

    private suspend fun handleCountrySelected(newCountry: String) {
        withLoading {
            repository.updateCountry(newCountry)
        }
        resetDialogs()
    }

    private suspend fun handleCountryDropdownClicked() {
        resetDialogs()
        _countryDialogVisible.emit(true)
    }

    private suspend fun resetDialogs() {
        _countryDialogVisible.emit(false)
        _languageDialogVisible.emit(false)
    }

    private suspend fun withLoading(f: suspend () -> Unit) {
        _loading.emit(true)

        f()

        _loading.emit(false)
    }

    // UI state (private)
    private val _loading = MutableStateFlow(false)
    private val _error = MutableStateFlow<String?>(null)

    private val _selectedCountry = MutableStateFlow(defaultUserPreferences.country)
    private val _countryList = MutableStateFlow(listOf(defaultUserPreferences.country))
    private val _countryDialogVisible = MutableStateFlow(false)

    private val _selectedLanguage = MutableStateFlow(defaultUserPreferences.language)
    private val _allLanguages = MutableStateFlow(Language.values().toList())
    private val _languageDialogVisible = MutableStateFlow(false)

    private val _isLightThemeSelected = MutableStateFlow(false)


    // UI state (public)
    val loading: StateFlow<Boolean> = _loading
    val error: StateFlow<String?> = _error

    val selectedCountry: StateFlow<String> = _selectedCountry
    val countryList: StateFlow<List<String>> = _countryList
    val countryDialogVisible: StateFlow<Boolean> = _countryDialogVisible

    val selectedLanguage: StateFlow<Language> = _selectedLanguage
    val allLanguages: StateFlow<List<Language>> = _allLanguages
    val languageDialogVisible: StateFlow<Boolean> = _languageDialogVisible

    val isLightThemeSelected: StateFlow<Boolean> = _isLightThemeSelected
}