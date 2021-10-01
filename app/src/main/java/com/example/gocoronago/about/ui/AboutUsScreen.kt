package com.example.gocoronago.about.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gocoronago.R
import com.example.gocoronago.about.model.Contributor
import com.example.gocoronago.about.viewmodel.AboutUsViewModel


@Composable
fun AboutUsScreen(
    viewmodel: AboutUsViewModel = hiltViewModel()
) {
    val versionName by viewmodel.versionName
    val contributors by viewmodel.contributors

    Column(modifier = Modifier.fillMaxSize()) {
        TitleBlock()
        Divider(color = Color(0x37000000), thickness = 1.dp)
        VersionBlock(versionName)
        Divider(color = Color(0x37000000), thickness = 1.dp)
        ContributorsBlock(contributors)
        Divider(color = Color(0x37000000), thickness = 1.dp)
    }
}

@Composable
fun TitleBlock() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp), horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier.size(100.dp),
            painter = painterResource(id = R.drawable.ic_about),
            contentDescription = null
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = stringResource(id = R.string.gocoronago),
            style = TextStyle(fontSize = 28.sp, color = Color(0xFF757575))
        )
        Text(
            text = stringResource(id = R.string.app_name),
            style = TextStyle(fontSize = 16.sp, color = Color(0xFF757575))
        )
    }
}

@Composable
fun VersionBlock(
    versionName: String
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.version),
            style = TextStyle(fontSize = 18.sp, color = Color(0xFF757575))
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(text = versionName, style = TextStyle(fontSize = 14.sp, color = Color(0xFF757575)))
    }
}

@Composable
fun ContributorsBlock(
    contributors: List<Contributor>
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(
            text = stringResource(id = R.string.contributors),
            style = TextStyle(fontSize = 18.sp, color = Color(0xFF757575))
        )
        Spacer(modifier = Modifier.height(4.dp))
        contributors.forEach {
            var contributorString = "${it.flag} ${it.name}"
            if (it.owner) {
                contributorString += " (Owner)"
            }
            Text(
                text = contributorString,
                style = TextStyle(fontSize = 14.sp, color = Color(0xFF757575))
            )
            Spacer(modifier = Modifier.height(4.dp))
        }
    }
}
