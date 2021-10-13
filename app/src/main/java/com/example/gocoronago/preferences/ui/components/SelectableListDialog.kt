package com.example.gocoronago.preferences.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.gocoronago.ui.utils.noRippleClickable

@Composable
@ExperimentalAnimationApi
fun <T> SelectableListDialog(
    visible: Boolean,
    items: List<T>,
    selectedItem: T,
    onNewItemSelected: (T) -> Unit,
    itemStingBuilder: (T) -> String,
    onDialogDismissRequested: () -> Unit,
    modifier: Modifier = Modifier
) {
    AnimatedVisibility(visible = visible) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.DarkGray.copy(alpha = 0.4f))
                .noRippleClickable { onDialogDismissRequested() },
            contentAlignment = Alignment.Center
        ) {
            Surface(
                elevation = 75.dp,
                color = MaterialTheme.colors.surface,
                modifier = modifier
                    .fillMaxWidth(.9f),
                shape = MaterialTheme.shapes.large,
            ) {
                LazyColumn(
                    modifier = Modifier.padding(16.dp)
                ) {
                    itemsIndexed(items) { index, item: T ->
                        Text(
                            text = itemStingBuilder(item),
                            fontWeight = if (item == selectedItem)
                                FontWeight.Bold else FontWeight.Normal,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable { onNewItemSelected(item) }
                                .padding(vertical = 8.dp, horizontal = 16.dp),
                            color = MaterialTheme.colors.onSurface
                        )
                        if (index < items.lastIndex)
                            Divider(color = Color.Black, thickness = 1.dp)
                    }
                }
            }
        }
    }
}