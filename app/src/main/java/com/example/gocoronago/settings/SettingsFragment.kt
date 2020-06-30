package com.example.gocoronago.settings

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.gocoronago.R

class SettingsFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}