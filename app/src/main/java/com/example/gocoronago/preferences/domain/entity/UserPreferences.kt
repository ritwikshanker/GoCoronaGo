package com.example.gocoronago.preferences.domain.entity

import com.example.gocoronago.preferences.domain.constants.ColorTheme
import com.example.gocoronago.preferences.domain.constants.Language

data class UserPreferences(
    val country: String,
    val theme: ColorTheme,
    val language: Language
)

val defaultUserPreferences = UserPreferences(
    country = "India",
    theme = ColorTheme.LIGHT,
    language = Language.ENGLISH
)
