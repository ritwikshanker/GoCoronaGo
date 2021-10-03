package com.example.gocoronago.tipspage.ui

import androidx.annotation.RawRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.gocoronago.tipspage.model.Tip
import com.example.gocoronago.tipspage.model.tipList
import com.example.gocoronago.ui.theme.AppTheme
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager

@ExperimentalPagerApi
@Composable
fun TipsViewPager(
    tips: List<Tip>,
) {
    HorizontalPager(count = tips.size) { index ->
        val tip = tips[index]
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            AnimSection(
                anim = tip.lottieAnimId,
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.7f)
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = stringResource(id = tip.stringRes))
        }
    }
}

@Composable
fun AnimSection(
    @RawRes anim: Int,
    modifier: Modifier = Modifier
) {
    val composition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(anim)
    )

    LottieAnimation(
        composition = composition,
        iterations = LottieConstants.IterateForever,
        modifier = modifier
    )
}

// todo: figure out why previews are not working!!
@ExperimentalPagerApi
@Preview(showBackground = true)
@Composable
fun TipsViewPagerPreview() {
    AppTheme {
        TipsViewPager(tips = tipList)
    }
}