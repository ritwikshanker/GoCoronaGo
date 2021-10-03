package com.example.gocoronago.ui.main

import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ProgressBar
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.gocoronago.homepage.Summary
import com.example.gocoronago.MainActivity
import com.example.gocoronago.R
import com.example.gocoronago.base.BaseFragment
import com.example.gocoronago.base.RequestResult
import com.example.gocoronago.databinding.MainFragmentBinding
import com.example.gocoronago.stayHome.StayHomeActivity
import kotlinx.android.synthetic.main.main_fragment.*
import kotlinx.android.synthetic.main.stay_home.*
import kotlinx.android.synthetic.main.total_cases.*
import kotlinx.android.synthetic.main.total_cured.*
import kotlinx.android.synthetic.main.total_death.*

class MainFragment : BaseFragment<MainFragmentBinding>(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var viewModel: MainViewModel

    private lateinit var covidResponse: Summary
    private lateinit var adapter: MainAdapter
    private lateinit var itemDecorator: MainItemDecorator
    private lateinit var countriesList: ArrayList<Any>

    override fun layoutResourceId(): Int = R.layout.main_fragment

    override fun initViewCreated() {
        countriesList = arrayListOf(getString(R.string.worldwide))
        setupNavBar()
        init()
    }

    private fun init() {
        initViewModel()
        initViewModelObservers()
//        initNetworkContainer()
        initClickListeners()
    }

    private fun initClickListeners() {
        stay_home_ll.setOnClickListener {
            val intent = Intent(context, StayHomeActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, MainViewModelFactory())
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
                showLoading()
            }
            is RequestResult.Success -> {
                onGetCovidSummarySuccess(requestResult)
                progress_bar_api.visibility=View.GONE
                error_img.visibility=View.GONE
                error_txt.visibility=View.GONE
            }
            is RequestResult.Error -> {
               // viewModel.getCovidSummaryData()                 //was used earlier when loading was not implemented
                retry_btn.visibility=View.VISIBLE
                progress_bar_api.visibility=View.GONE
                error_img.visibility=View.VISIBLE
                error_txt.visibility=View.VISIBLE
                retry_btn.setOnClickListener {
                    retry()
                }
            }
        }

    }

    private fun showLoading() {
        progress_bar_api.visibility=View.VISIBLE
        error_img.visibility=View.GONE
        error_txt.visibility=View.GONE
    }
    private fun retry() {
        error_txt.visibility=View.GONE
        error_img.visibility=View.GONE
        retry_btn.visibility=View.GONE
        viewModel.getCovidSummaryData()
    }

    private fun onGetCovidSummarySuccess(response: RequestResult.Success<Any?>) {
        if (response.data is Summary?) {
            initSpinner(response.data)
            val covidResponse: Summary? = response.data
            covidResponse?.let {
                val totalCasesImage = (total_cases_iv) as LottieAnimationView
                totalCasesImage.imageAssetsFolder = ("images/raw")
                totalCasesImage.setAnimation(R.raw.coronavirussick)
                totalCasesImage.playAnimation()
                totalCasesImage.repeatCount = LottieDrawable.INFINITE
                totalCasesImage.disableExtraScaleModeInFitXY()
                total_cases_tv.visibility = View.VISIBLE
                total_cases_tv.text =
                    getString(R.string.total_confirmed, covidResponse.global.totalConfirmed)
                total_cases_increased_tv.visibility = View.VISIBLE
                total_cases_increased_tv.text =
                    "\u2191 " + covidResponse.global.newConfirmed.toString()
                val totalCasesIncreasedImage = (total_cured_iv) as LottieAnimationView
                totalCasesIncreasedImage.imageAssetsFolder = ("images/raw")
                totalCasesIncreasedImage.setAnimation(R.raw.covid19cured)
                totalCasesIncreasedImage.playAnimation()
                totalCasesIncreasedImage.repeatCount = LottieDrawable.INFINITE
                totalCasesIncreasedImage.disableExtraScaleModeInFitXY()
                total_cured_tv.visibility = View.VISIBLE
                total_cured_tv.text =
                    getString(R.string.total_cured, covidResponse.global.totalRecovered)
                total_cured_increased_tv.visibility = View.VISIBLE
                total_cured_increased_tv.text =
                    "\u2191 " + covidResponse.global.newRecovered.toString()
                val totalDeathsImage = (total_deaths_iv) as LottieAnimationView
                totalDeathsImage.imageAssetsFolder = ("images/raw")
                totalDeathsImage.setAnimation(R.raw.covid19)
                totalDeathsImage.playAnimation()
                totalDeathsImage.repeatCount = LottieDrawable.INFINITE
                totalDeathsImage.disableExtraScaleModeInFitXY()
                total_deaths_tv.visibility = View.VISIBLE
                total_deaths_tv.text =
                    getString(R.string.total_deaths, covidResponse.global.totalDeaths)
                total_deaths_increased_tv.visibility = View.VISIBLE
                total_deaths_increased_tv.text =
                    "\u2191 " + covidResponse.global.newDeaths.toString()
                stay_home_ll.visibility = View.VISIBLE
                val stayHomeImage = (stay_home_iv) as LottieAnimationView
                stayHomeImage.imageAssetsFolder = ("images/raw")
                stayHomeImage.setAnimation(R.raw.staysafestayhome)
                stayHomeImage.playAnimation()
                stayHomeImage.repeatCount = LottieDrawable.INFINITE
                stayHomeImage.disableExtraScaleModeInFitXY()
            }
        }
    }

    private fun initSpinner(data: Summary?) {
        if (data != null) {
            covidResponse = data
        }
        for (i in covidResponse.countries!!) {
            countriesList.add(i.country)
        }
        adapter = MainAdapter(requireContext())
        itemDecorator = MainItemDecorator(requireContext())
        val aa = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, countriesList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(mySpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@MainFragment
            prompt = context.getString(R.string.select_country)
            gravity = Gravity.CENTER
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = getString(R.string.nothing_selected))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 -> showToast(
                message = getString(R.string.item_selected_1, position, countriesList[position])
            )
            else -> {
                showToast(message = getString(R.string.country_selected, countriesList[position]))
                setCountryData(countriesList[position] as String, covidResponse)
            }
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }

    private fun showToastLong(message: String, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(context, message, duration).show()
    }

    private fun setCountryData(
        countryName: String,
        covidResponse: Summary?
    ) {
        if (countryName == getString(R.string.worldwide)) {
            covidResponse?.let {
                total_cases_tv.visibility = View.VISIBLE
                total_cases_tv.text =
                    getString(R.string.total_confirmed, covidResponse.global.totalConfirmed)
                total_cases_increased_tv.visibility = View.VISIBLE
                total_cases_increased_tv.text =
                    "\u2191 " + covidResponse.global.newConfirmed.toString()
                total_cured_tv.visibility = View.VISIBLE
                total_cured_tv.text =
                    getString(R.string.total_cured, covidResponse.global.totalRecovered)
                total_cured_increased_tv.visibility = View.VISIBLE
                total_cured_increased_tv.text =
                    "\u2191 " + covidResponse.global.newRecovered.toString()
                total_deaths_tv.visibility = View.VISIBLE
                total_deaths_tv.text =
                    getString(R.string.total_deaths, covidResponse.global.totalDeaths)
                total_deaths_increased_tv.visibility = View.VISIBLE
                total_deaths_increased_tv.text =
                    "\u2191 " + covidResponse.global.newDeaths.toString()
            }
        } else {
            for (i in covidResponse?.countries!!) {
                if (countryName == i.country) {
                    covidResponse.let {
                        total_cases_tv.visibility = View.VISIBLE
                        total_cases_tv.text =
                            getString(R.string.total_confirmed, i.totalConfirmed)
                        total_cases_increased_tv.visibility = View.VISIBLE
                        total_cases_increased_tv.text =
                            "\u2191 " + i.newConfirmed.toString()
                        total_cured_tv.visibility = View.VISIBLE
                        total_cured_tv.text =
                            getString(R.string.total_cured, i.totalRecovered)
                        total_cured_increased_tv.visibility = View.VISIBLE
                        total_cured_increased_tv.text =
                            "\u2191 " + i.newRecovered.toString()
                        total_deaths_tv.visibility = View.VISIBLE
                        total_deaths_tv.text =
                            getString(R.string.total_deaths, i.totalDeaths)
                        total_deaths_increased_tv.visibility = View.VISIBLE
                        total_deaths_increased_tv.text =
                            "\u2191 " + i.newDeaths.toString()
                    }
                }
            }
        }
    }

    private fun setupNavBar() {
        (activity as? MainActivity)?.apply {
            title = getString(R.string.app_name)
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            supportActionBar?.setHomeButtonEnabled(false)
        }
    }

}