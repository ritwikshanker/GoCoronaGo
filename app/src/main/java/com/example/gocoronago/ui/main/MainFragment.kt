package com.example.gocoronago.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gocoronago.HomePage.Summary
import com.example.gocoronago.R
import com.example.gocoronago.base.RequestResult
import kotlinx.android.synthetic.main.total_cases.*
import kotlinx.android.synthetic.main.total_cured.*
import kotlinx.android.synthetic.main.total_death.*

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
//        initRV()
        initViewModel()
        initViewModelObservers()
//        initNetworkContainer()
    }

    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this, MainViewModelFactory())
            .get(MainViewModel::class.java)
    }

    private fun initViewModelObservers() {
        viewModel.covidSummaryData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                onGetCovidSummary(it)
            }
        })
    }

    private fun onGetCovidSummary(requestResult: RequestResult<Any?>) {
        when (requestResult) {
            is RequestResult.Loading -> {
//                showLoading()
            }
            is RequestResult.Success -> {
                onGetCovidSummarySuccess(requestResult as RequestResult.Success)
            }
            is RequestResult.Error -> {
//                showErrorState()
            }
        }

    }

    private fun onGetCovidSummarySuccess(response: RequestResult.Success<Any?>) {
        if (response.data is Summary?) {
            val covidResponse: Summary? = response.data

            covidResponse?.let {
                total_cases_tv.visibility = View.VISIBLE
                total_cases_tv.text =
                    "Total Confirmed Cases \n " + covidResponse.global.totalConfirmed.toString()
                total_cases_increased_tv.visibility = View.VISIBLE
                total_cases_increased_tv.text =
                    "\u2191 " + covidResponse.global.newConfirmed.toString()
                total_cured_tv.visibility = View.VISIBLE
                total_cured_tv.text =
                    "Total Cured Cases \n " + covidResponse.global.totalRecovered.toString()
                total_cured_increased_tv.visibility = View.VISIBLE
                total_cured_increased_tv.text =
                    "\u2191 " + covidResponse.global.newRecovered.toString()
                total_deaths_tv.visibility = View.VISIBLE
                total_deaths_tv.text =
                    "Total Deaths \n " + covidResponse.global.totalDeaths.toString()
                total_deaths_increased_tv.visibility = View.VISIBLE
                total_deaths_increased_tv.text =
                    "\u2191 " + covidResponse.global.newDeaths.toString()
            }
        }
    }


}