package com.example.gocoronago.about.repository

import com.example.gocoronago.BuildConfig
import com.example.gocoronago.about.model.Contributor
import javax.inject.Inject

interface IAboutUsRepository {
    fun getVersionName(): String
    fun getContributorList(): List<Contributor>
}


class AboutUsRepository @Inject constructor() : IAboutUsRepository {

    override fun getVersionName(): String {
        return BuildConfig.VERSION_NAME
    }

    override fun getContributorList(): List<Contributor> {
        return listOf(
            Contributor("Ritwik Shanker", "🇮🇳", true),
            Contributor("Hardik Sachan", "🇮🇳"),
            Contributor("Sunny", "🇮🇳"),
            Contributor("M. Asrof Bayhaqqi", "🇮🇩"),
            Contributor("Jacob", "\uD83C\uDDF7\uD83C\uDDFA"),
            Contributor("Matthew Scibilia", "\uD83C\uDDE6\uD83C\uDDFA"),
            Contributor("MR Abdhi P", "🇮🇩"),
            Contributor("Ben Kadel", "\uD83C\uDDEC\uD83C\uDDE7")
        )
    }
}