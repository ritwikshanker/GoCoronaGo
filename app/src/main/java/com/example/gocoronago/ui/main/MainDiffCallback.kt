package com.example.gocoronago.ui.main

import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.DiffUtil
import com.example.gocoronago.HomePage.CountriesItem

class MoviesDiffCallback(context: Context) : DiffUtil.ItemCallback<Any>() {
    override fun areItemsTheSame(oldItem: Any, newItem: Any): Boolean {
        var same = false
        if (oldItem is CountriesItem && newItem is CountriesItem) {
            same = oldItem.country == newItem.country
        }
        return same
    }

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: Any, newItem: Any): Boolean {
        var same = false
        if (oldItem is CountriesItem && newItem is CountriesItem) {
            same = oldItem == newItem
        }
        return same
    }

}
