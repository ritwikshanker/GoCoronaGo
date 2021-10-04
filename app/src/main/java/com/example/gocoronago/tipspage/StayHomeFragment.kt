package com.example.gocoronago.tipspage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.DialogFragment
import com.example.gocoronago.MainActivity
import com.example.gocoronago.R
import com.example.gocoronago.tipspage.ui.TipsPage
import com.example.gocoronago.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
class StayHomeFragment : DialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupNavBar()
    }

    private fun setupNavBar() {
        (activity as? MainActivity)?.apply {
            title = getString(R.string.stay_home_tips)
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setHomeButtonEnabled(true)
        }
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setViewCompositionStrategy(
            ViewCompositionStrategy.DisposeOnLifecycleDestroyed(viewLifecycleOwner)
        )

        setContent {
            AppTheme {
                TipsPage()
            }
        }
    }


    companion object {
        fun newInstance() = StayHomeFragment()
    }
}