package com.example.gocoronago.preferences.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gocoronago.preferences.domain.constants.Language
import com.example.gocoronago.preferences.ui.components.CountryDialog
import com.example.gocoronago.preferences.ui.components.LanguageDialog
import com.example.gocoronago.preferences.ui.components.LoadingOverlay
import com.example.gocoronago.preferences.ui.components.PreferencesHomePage
import com.example.gocoronago.preferences.viewmodel.PreferencesScreenEvent
import com.example.gocoronago.preferences.viewmodel.PreferencesViewModel

@ExperimentalAnimationApi
@Composable
fun PreferencesPage(
    viewModel: PreferencesViewModel = hiltViewModel()
) {
    val loading = viewModel.loading.collectAsState()

    val languageDialogVisible = viewModel.languageDialogVisible.collectAsState()
    val languages = viewModel.allLanguages.collectAsState()
    val selectedLanguage = viewModel.selectedLanguage.collectAsState()

    val countryDialogVisible = viewModel.countryDialogVisible.collectAsState()
    val countries = viewModel.countryList.collectAsState()
    val selectedCountry = viewModel.selectedCountry.collectAsState()

    val isLightThemeSelected = viewModel.isLightThemeSelected.collectAsState()

    PreferencesPage(
        loading = loading.value,
        languageDialogVisible = languageDialogVisible.value,
        languages = languages.value,
        selectedLanguage = selectedLanguage.value,
        onNewLanguageSelected = {
            viewModel.onEvent(PreferencesScreenEvent.LanguageSelected(it))
        },
        countryDialogVisible = countryDialogVisible.value,
        countries = countries.value,
        selectedCountry = selectedCountry.value,
        onNewCountrySelected = {
            viewModel.onEvent(PreferencesScreenEvent.CountrySelected(it))
        },
        isLightThemeSelected = isLightThemeSelected.value,
        onEditCountryClicked = {
            viewModel.onEvent(PreferencesScreenEvent.CountryDropdownClicked)
        },
        onEditLanguageClicked = {
            viewModel.onEvent(PreferencesScreenEvent.LanguageDropdownClicked)
        },
        onToggleThemeClicked = {
            viewModel.onEvent(PreferencesScreenEvent.ThemeToggled)
        },
        onDialogDismissRequested = {
            viewModel.onEvent(PreferencesScreenEvent.DialogDismissed)
        }
    )
}

@ExperimentalAnimationApi
@Composable
private fun PreferencesPage(
    loading: Boolean,
    languageDialogVisible: Boolean,
    languages: List<Language>,
    selectedLanguage: Language,
    onNewLanguageSelected: (Language) -> Unit,
    countryDialogVisible: Boolean,
    countries: List<String>,
    selectedCountry: String,
    onNewCountrySelected: (String) -> Unit,
    isLightThemeSelected: Boolean,
    onEditCountryClicked: () -> Unit,
    onEditLanguageClicked: () -> Unit,
    onToggleThemeClicked: () -> Unit,
    onDialogDismissRequested: () -> Unit
) {
    Box(
        contentAlignment = Alignment.Center
    ) {

        PreferencesHomePage(
            modifier = Modifier
                .align(Alignment.TopStart)
                .fillMaxWidth(),
            selectedCountry = selectedCountry,
            selectedLanguage = selectedLanguage,
            isLightThemeSelected = isLightThemeSelected,
            onEditCountryClicked = onEditCountryClicked,
            onEditLanguageClicked = onEditLanguageClicked,
            onToggleThemeClicked = onToggleThemeClicked
        )

        CountryDialog(
            visible = countryDialogVisible,
            countries = countries,
            selectedCountry = selectedCountry,
            onNewCountrySelected = onNewCountrySelected,
            onDialogDismissRequested = onDialogDismissRequested
        )

        LanguageDialog(
            visible = languageDialogVisible,
            languages = languages,
            selectedLanguage = selectedLanguage,
            onNewLanguageSelected = onNewLanguageSelected,
            onDialogDismissRequested = onDialogDismissRequested
        )

        LoadingOverlay(
            loading = loading
        )
    }
}