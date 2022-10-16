package com.example.gocoronago.homepage.models


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class WorldwideData(
    @SerializedName("active")
    var active: Double?, // 0
    @SerializedName("activePerOneMillion")
    var activePerOneMillion: Double?, // 0
    @SerializedName("affectedCountries")
    var affectedCountries: Double?, // 0
    @SerializedName("cases")
    var cases: Double?, // 0
    @SerializedName("casesPerOneMillion")
    var casesPerOneMillion: Double?, // 0
    @SerializedName("critical")
    var critical: Double?, // 0
    @SerializedName("criticalPerOneMillion")
    var criticalPerOneMillion: Double?, // 0
    @SerializedName("deaths")
    var deaths: Double?, // 0
    @SerializedName("deathsPerOneMillion")
    var deathsPerOneMillion: Double?, // 0
    @SerializedName("oneCasePerPeople")
    var oneCasePerPeople: Double?, // 0
    @SerializedName("oneDeathPerPeople")
    var oneDeathPerPeople: Double?, // 0
    @SerializedName("oneTestPerPeople")
    var oneTestPerPeople: Double?, // 0
    @SerializedName("population")
    var population: Double?, // 0
    @SerializedName("recovered")
    var recovered: Double?, // 0
    @SerializedName("recoveredPerOneMillion")
    var recoveredPerOneMillion: Double?, // 0
    @SerializedName("tests")
    var tests: Double?, // 0
    @SerializedName("testsPerOneMillion")
    var testsPerOneMillion: Double?, // 0
    @SerializedName("todayCases")
    var todayCases: Double?, // 0
    @SerializedName("todayRecovered")
    var todayRecovered: Double?, // 0
    @SerializedName("updated")
    var updated: Double? // 0
)