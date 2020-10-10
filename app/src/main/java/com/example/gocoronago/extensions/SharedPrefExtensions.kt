package com.example.gocoronago.extensions

import android.app.Activity
import android.content.Context
import androidx.core.content.edit
import com.example.gocoronago.R

private const val DEFAULT_INT_VALUE = 0

private fun Activity.getKey(keySuffix: String): String =
    getString(R.string.sharedpref_key_prefix, keySuffix)

/**
 * Saves the current [keySuffix] - [value] pair to to [android.content.SharedPreferences]
 */
fun Activity.saveInt(keySuffix: String, value: Int, mode: Int = Context.MODE_PRIVATE): Unit =
    getPreferences(mode).edit {
        putInt(getKey(keySuffix), value)
    }

/**
 * Retrieves the value paired to this [keySuffix]
 */
fun Activity.readInt(keySuffix: String, mode: Int = Context.MODE_PRIVATE): Int =
    getPreferences(mode).getInt(getKey(keySuffix), DEFAULT_INT_VALUE)