package com.example.gocoronago.stayHome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.airbnb.lottie.LottieAnimationView
import com.airbnb.lottie.LottieDrawable
import com.example.gocoronago.R
import kotlinx.android.synthetic.main.stay_home_item.*


class StayHomeDataFragment : Fragment() {

    companion object {
        const val TEXT = "text"
        const val ID = "animation_id"
        fun newInstance(bundle: Bundle): StayHomeDataFragment {
            val onBoardingDataFragment =
                StayHomeDataFragment()
            onBoardingDataFragment.arguments = bundle
            return onBoardingDataFragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.stay_home_item, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }


    private fun init() {
        initBundle()
        initSetData()
    }

    private var resId = 0
    private var textView: String = ""
    private fun initBundle() {
        resId = arguments?.getInt(ID)!!
        textView = arguments?.getString(TEXT).toString()
    }

    private fun initSetData() {
        val lottieAnimationView = (animated_view) as LottieAnimationView
        lottieAnimationView.imageAssetsFolder = ("images/raw")
        lottieAnimationView.setAnimation(resId)
        lottieAnimationView.playAnimation()
        lottieAnimationView.repeatCount = LottieDrawable.INFINITE
        animated_tv.text = textView
    }
}