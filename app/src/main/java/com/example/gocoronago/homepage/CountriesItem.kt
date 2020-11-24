package com.example.gocoronago.homepage

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class CountriesItem(
    @SerializedName("NewRecovered")
    val newRecovered: Int = 0,
    @SerializedName("NewDeaths")
    val newDeaths: Int = 0,
    @SerializedName("TotalRecovered")
    val totalRecovered: Int = 0,
    @SerializedName("TotalConfirmed")
    val totalConfirmed: Int = 0,
    @SerializedName("Country")
    val country: String = "",
    @SerializedName("CountryCode")
    val countryCode: String = "",
    @SerializedName("Slug")
    val slug: String = "",
    @SerializedName("NewConfirmed")
    val newConfirmed: Int = 0,
    @SerializedName("TotalDeaths")
    val totalDeaths: Int = 0,
    @SerializedName("Date")
    val date: String = ""
)