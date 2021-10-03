package com.example.gocoronago.tipspage.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gocoronago.tipspage.viewmodel.TipsViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@Composable
fun TipsPage(
    vm: TipsViewModel = hiltViewModel()
) {
    val tips = vm.tips.collectAsState()

    TipsViewPager(tips = tips.value)
}