package com.example.gocoronago.about.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.gocoronago.about.model.Contributor
import com.example.gocoronago.about.usecase.IGetContributorListUseCase
import com.example.gocoronago.about.usecase.IGetVersionNameUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AboutUsViewModel @Inject constructor(
    getVersionNameUseCase: IGetVersionNameUseCase,
    getContributorListUseCase: IGetContributorListUseCase
) : ViewModel() {

    private val _versionName: MutableState<String> = mutableStateOf(getVersionNameUseCase())
    val versionName: State<String> = _versionName

    private val _contributors: MutableState<List<Contributor>> = mutableStateOf(getContributorListUseCase())
    val contributors: State<List<Contributor>> = _contributors
}