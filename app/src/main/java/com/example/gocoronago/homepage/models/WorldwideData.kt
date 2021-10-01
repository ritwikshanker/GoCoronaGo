package com.example.gocoronago.homepage.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WorldwideData(
    @SerializedName("active")
    var active: Int?, // 0
    @SerializedName("activePerOneMillion")
    var activePerOneMillion: Int?, // 0
    @SerializedName("affectedCountries")
    var affectedCountries: Int?, // 0
    @SerializedName("cases")
    var cases: Int?, // 0
    @SerializedName("casesPerOneMillion")
    var casesPerOneMillion: Int?, // 0
    @SerializedName("critical")
    var critical: Int?, // 0
    @SerializedName("criticalPerOneMillion")
    var criticalPerOneMillion: Int?, // 0
    @SerializedName("deaths")
    var deaths: Int?, // 0
    @SerializedName("deathsPerOneMillion")
    var deathsPerOneMillion: Int?, // 0
    @SerializedName("oneCasePerPeople")
    var oneCasePerPeople: Int?, // 0
    @SerializedName("oneDeathPerPeople")
    var oneDeathPerPeople: Int?, // 0
    @SerializedName("oneTestPerPeople")
    var oneTestPerPeople: Int?, // 0
    @SerializedName("population")
    var population: Int?, // 0
    @SerializedName("recovered")
    var recovered: Int?, // 0
    @SerializedName("recoveredPerOneMillion")
    var recoveredPerOneMillion: Int?, // 0
    @SerializedName("tests")
    var tests: Int?, // 0
    @SerializedName("testsPerOneMillion")
    var testsPerOneMillion: Int?, // 0
    @SerializedName("todayCases")
    var todayCases: Int?, // 0
    @SerializedName("todayRecovered")
    var todayRecovered: Int?, // 0
    @SerializedName("updated")
    var updated: Int? // 0
)