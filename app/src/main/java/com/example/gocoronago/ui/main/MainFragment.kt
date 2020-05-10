package com.example.gocoronago.ui.main

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.gocoronago.HomePage.Summary
import com.example.gocoronago.R
import com.example.gocoronago.base.RequestResult
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.total_cases.*
import kotlinx.android.synthetic.main.total_cured.*
import kotlinx.android.synthetic.main.total_death.*

class MainFragment : Fragment(), AdapterView.OnItemSelectedListener {

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
            initRV(response.data)
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

    private val NEW_SPINNER_ID = 1

    private var countriesList: ArrayList<Any> = arrayListOf("Worldwide")
    private lateinit var covidResponse: Summary
    private lateinit var adapter: MainAdapter
    private lateinit var itemDecorator: MainItemDecorator
    private fun initRV(data: Summary?) {
        if (data != null) {
            covidResponse = data
        }
        for (i in covidResponse?.countries!!) {
            countriesList.add(i.country)
        }
        adapter = MainAdapter(
            requireContext()
        )
        itemDecorator = MainItemDecorator(requireContext())
        var aa =
            context?.let { ArrayAdapter(it, android.R.layout.simple_spinner_item, countriesList) }
        aa?.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(mySpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@MainFragment
            prompt = "Select Country"
            gravity = Gravity.CENTER

        }

        val spinner = Spinner(this.context)
        spinner.id = NEW_SPINNER_ID

        val ll = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        ll.setMargins(10, 40, 10, 10)
        countries_ll.addView(spinner)

        aa = context?.let { ArrayAdapter(it, R.layout.country_item, countriesList) }
        aa?.setDropDownViewResource(R.layout.country_item)

        with(spinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@MainFragment
            layoutParams = ll
            prompt = "Select country"
            setPopupBackgroundResource(R.color.material_grey_600)

        }
    }

    private fun submitList(movieList: List<Any>) {
        adapter.submitList(movieList)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = "Nothing Selected")
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 -> showToast(message = "Spinner 2 Position:${position} and language: ${countriesList[position]}")
            else -> {
                showToast(message = "Country Selected : ${countriesList[position]}")
                setCountryData(countriesList[position] as String, covidResponse)
            }
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    private fun setCountryData(
        countryName: String,
        covidResponse: Summary?
    ) {
        if (countryName == "Worldwide") {
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
        } else {
            for (i in covidResponse?.countries!!) {
                if (countryName == i.country) {
                    covidResponse?.let {
                        total_cases_tv.visibility = View.VISIBLE
                        total_cases_tv.text =
                            "Total Confirmed Cases \n " + i.totalConfirmed.toString()
                        total_cases_increased_tv.visibility = View.VISIBLE
                        total_cases_increased_tv.text =
                            "\u2191 " + i.newConfirmed.toString()
                        total_cured_tv.visibility = View.VISIBLE
                        total_cured_tv.text =
                            "Total Cured Cases \n " + i.totalRecovered.toString()
                        total_cured_increased_tv.visibility = View.VISIBLE
                        total_cured_increased_tv.text =
                            "\u2191 " + i.newRecovered.toString()
                        total_deaths_tv.visibility = View.VISIBLE
                        total_deaths_tv.text =
                            "Total Deaths \n " + i.totalDeaths.toString()
                        total_deaths_increased_tv.visibility = View.VISIBLE
                        total_deaths_increased_tv.text =
                            "\u2191 " + i.newDeaths.toString()
                    }
                }
            }
        }
    }

}