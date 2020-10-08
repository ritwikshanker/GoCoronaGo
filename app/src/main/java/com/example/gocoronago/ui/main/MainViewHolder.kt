package com.example.gocoronago.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gocoronago.HomePage.CountriesItem
import com.example.gocoronago.R
import com.example.gocoronago.databinding.MainFragmentBinding

class MainViewHolder(val binding: MainFragmentBinding) : RecyclerView.ViewHolder(binding.root) {

    companion object {
        const val layout = R.layout.country_item
        fun create(
            context: Context, inflater: LayoutInflater,
            viewGroup: ViewGroup
        ): MainViewHolder {
            val binding = DataBindingUtil.inflate<MainFragmentBinding>(
                inflater,
                layout, viewGroup, false
            )
            return MainViewHolder(
                binding
            )
        }
    }

    fun bind(countriesItem: CountriesItem) {
//        binding.mySpinner.country_name_tv.text = countriesItem.country
    }
}