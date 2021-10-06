package com.example.gocoronago.preferences.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@ExperimentalAnimationApi
fun CountryDialog(
    visible: Boolean,
    countries: List<String>,
    selectedCountry: String,
    onNewCountrySelected: (String) -> Unit,
    onDialogDismissRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    SelectableListDialog(
        visible = visible,
        items = countries,
        selectedItem = selectedCountry,
        onNewItemSelected = onNewCountrySelected,
        itemStingBuilder = { it },
        onDialogDismissRequested = onDialogDismissRequested,
        modifier = modifier
    )
}