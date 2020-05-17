package com.example.gocoronago.stayHome

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.gocoronago.R
import com.example.gocoronago.ui.main.MainFragment
import kotlinx.android.synthetic.main.stay_home_vp.*

class StayHomeFragment : DialogFragment() {

    companion object {
        fun start(fm: FragmentManager) {
            val fragment =
                StayHomeFragment()
            fragment.show(fm, "StayHomeActivity")
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.let {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            it.window?.setLayout(width, height)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        dialog?.window?.setBackgroundDrawable(ColorDrawable(Color.WHITE))
        return inflater.inflate(R.layout.stay_home_vp, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initClickListener()
        init()
        initAdapter()
    }

    private fun init() {
        initLoadFragmentData()
        setViewPager()
        initSetFragments()
    }

    private var fragmentData = mutableListOf<Pair<Int, String>>()
    private fun initLoadFragmentData() {
        fragmentData.add(
            Pair(
                R.raw.wear_mask,
                "Cover mouth and nose with mask and make sure there are no gaps between your face and the mask."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.staysafestayhome,
                "Stay Home Stay Safe, Let's Stop COVID-19, stay home in COVID-19 coronavirus outbreak, stay in the house to prevent virus infection."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.wash_your_hands,
                "Wash your hands with soap and water, and dry them thoroughly."
            )
        )
    }

    private fun setViewPager() {
        stay_home_tl.setupWithViewPager(stay_home_vp)
        stay_home_vp.offscreenPageLimit = fragmentData.size
        stay_home_vp.id = R.id.stay_home_vp
    }

    private fun initClickListener() {
//        start_bt.setOnClickListener {
//            onDestroyView()
//        }
    }

    private var fragments = mutableListOf<Fragment>()
    private var titles = mutableListOf<String>()
    private fun initSetFragments() {
        fragmentData.forEach {
            val bundle = Bundle()
            bundle.putInt(StayHomeDataFragment.ID, it.first)
            bundle.putString(StayHomeDataFragment.TEXT, it.second)
            fragments.add(StayHomeDataFragment.newInstance(bundle))
            titles.add("sddasd")
        }
    }

    private fun initAdapter() {
        stay_home_vp.adapter =
            StayHomeAdapter(
                childFragmentManager,
                fragments,
                titles
            )
    }
}