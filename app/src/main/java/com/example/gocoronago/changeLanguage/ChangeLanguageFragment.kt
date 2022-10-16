package com.example.gocoronago.changeLanguage

import android.R
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.gocoronago.MainActivity
import kotlinx.android.synthetic.main.main_fragment.mySpinner

class ChangeLanguageFragment : Fragment(), AdapterView.OnItemSelectedListener {

    companion object {
        fun newInstance() = ChangeLanguageFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View =
        inflater.inflate(com.example.gocoronago.R.layout.change_language_fragment, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavBar()
        initSpinner()
    }

    private fun setupNavBar() {
        (activity as? MainActivity)?.apply {
            title = getString(com.example.gocoronago.R.string.change_language)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        menu.findItem(com.example.gocoronago.R.id.change_language)?.isVisible = false
        super.onPrepareOptionsMenu(menu)
    }

    private val languageList = listOf(
        "English",
        "Czech",
        "German",
        "Spanish",
        "French",
        "Hindi",
        "Marathi",
        "Dutch",
        "Brazilian - Portuguese",
        "Mandarin - Simplified"
    )


    private fun initSpinner() {
        val aa = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, languageList)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        with(mySpinner)
        {
            adapter = aa
            setSelection(0, false)
            onItemSelectedListener = this@ChangeLanguageFragment
            prompt = context.getString(com.example.gocoronago.R.string.select_language)
            gravity = Gravity.CENTER
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
        showToast(message = getString(com.example.gocoronago.R.string.nothing_selected))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        when (view?.id) {
            1 -> showToast(
                message = getString(
                    com.example.gocoronago.R.string.item_selected_1,
                    position,
                    languageList[position]
                )
            )
            else -> {
                showToast(
                    message = getString(
                        com.example.gocoronago.R.string.language_changed,
                        languageList[position]
                    )
                )

            }
        }
    }

    private fun showToast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        Toast.makeText(context, message, duration).show()
    }
}