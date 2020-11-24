package com.example.gocoronago.homepage

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Summary(
    @SerializedName("Countries")
    val countries: List<CountriesItem>?,
    @SerializedName("Global")
    val global: Global,
    @SerializedName("Date")
    val date: String = ""
)