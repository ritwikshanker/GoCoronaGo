package com.example.gocoronago.preferences.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Switch
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gocoronago.R
import com.example.gocoronago.preferences.domain.constants.Language

@Composable
fun PreferencesHomePage(
    selectedLanguage: Language,
    selectedCountry: String,
    isLightThemeSelected: Boolean,
    onEditCountryClicked: () -> Unit,
    onEditLanguageClicked: () -> Unit,
    onToggleThemeClicked: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        RowItem(name = "Country", currentValue = selectedCountry) {
            onEditCountryClicked()
        }
        RowItem(name = "Language", currentValue = selectedLanguage.toString()) {
            onEditLanguageClicked()
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Theme")
            Spacer(modifier = Modifier.fillMaxWidth(0.3f))
            Text(text = "Light")
            Switch(checked = !isLightThemeSelected, onCheckedChange = { onToggleThemeClicked() })
            Text(
                text = "Dark",
                modifier = Modifier.padding(end = 16.dp)
            )
        }

    }

}

@Composable
private fun RowItem(
    name: String,
    currentValue: String,
    onValueChangeRequested: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = name)
        Spacer(modifier = Modifier.fillMaxWidth(0.5f))
        Text(text = currentValue)
        IconButton(onClick = onValueChangeRequested) {
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit Icon"
            )
        }
    }
}
