package com.example.gocoronago.tipspage.model

import androidx.annotation.RawRes
import androidx.annotation.StringRes
import com.example.gocoronago.R

data class Tip(
    @RawRes val lottieAnimId: Int,
    @StringRes val stringRes: Int
)

val tipList = listOf(
    Tip(
        R.raw.stay_safe_stay_home,
        R.string.stay_home_text
    ),
    Tip(
        R.raw.sneeze_covid,
        R.string.sneeze_text
    ),
    Tip(
        R.raw.wash_your_hands,
        R.string.wash_your_hands_text
    ),
    Tip(
        R.raw.social_distancing,
        R.string.social_distancing_text
    ),
    Tip(
        R.raw.loved_ones,
        R.string.loved_ones_text
    ),
)


