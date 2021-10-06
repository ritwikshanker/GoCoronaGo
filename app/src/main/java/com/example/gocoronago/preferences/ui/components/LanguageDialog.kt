package com.example.gocoronago.preferences.ui.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gocoronago.preferences.domain.constants.Language

@Composable
@ExperimentalAnimationApi
fun LanguageDialog(
    visible: Boolean,
    languages: List<Language>,
    selectedLanguage: Language,
    onNewLanguageSelected: (Language) -> Unit,
    onDialogDismissRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    SelectableListDialog(
        visible = visible,
        items = languages,
        selectedItem = selectedLanguage,
        onNewItemSelected = onNewLanguageSelected,
        itemStingBuilder = { lang ->
            lang.toString()
        },
        onDialogDismissRequested = onDialogDismissRequested,
        modifier = modifier
    )
}