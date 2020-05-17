package com.example.gocoronago.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gocoronago.base.RequestResult
import kotlinx.coroutines.launch

class MainViewModel() :
    ViewModel() {
    var covidSummaryData: MutableLiveData<RequestResult<Any>> = MutableLiveData()
    var mainRepo = MainRepo()

    init {
        getCovidSummaryData()
    }

    fun getCovidSummaryData() {
        viewModelScope.launch {
            covidSummaryData.value = RequestResult.Loading("")
            try {
                val result = mainRepo.getCovidSummary()
                covidSummaryData.value = RequestResult.Success(result)
            } catch (e: Exception) {
                covidSummaryData.value = RequestResult.Error(e)
            }
        }
    }


}