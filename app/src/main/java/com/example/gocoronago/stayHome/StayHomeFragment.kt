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
                "Cover mouth and nose with mask."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.stay_safe_stay_home,
                "Stay Home and avoid going out unless it is very important."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.sneeze,
                "Cover your nose and mouth with your bent elbow or a tissue when you cough or sneeze."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.wash_your_hands,
                "Clean your hands often. Use soap and water, or an alcohol-based hand rub."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.social_distancing,
                "Keep a safe distance from people whenever possible."
            )
        )
        fragmentData.add(
            Pair(
                R.raw.loved_ones,
                "Stay in touch with your loved ones so that no one feels alone in these tough times."
            )
        )
    }

    private fun setViewPager() {
        stay_home_tl.setupWithViewPager(stay_home_vp)
        stay_home_vp.offscreenPageLimit = fragmentData.size
        stay_home_vp.id = R.id.stay_home_vp
    }

    override fun onResume() {
        super.onResume()
        dialog?.setOnKeyListener { dialog, keyCode, event ->
            if ((keyCode == android.view.KeyEvent.KEYCODE_BACK)) {
                // To dismiss the fragment when the back-button is pressed.
                dismiss()
                true
            } else
                false// Otherwise, do nothing else
        }
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
            var count: Int = 0
            val bundle = Bundle()
            bundle.putInt(StayHomeDataFragment.ID, it.first)
            bundle.putString(StayHomeDataFragment.TEXT, it.second)
            fragments.add(StayHomeDataFragment.newInstance(bundle))
            titles.add(count.toString())
            count += 1;
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