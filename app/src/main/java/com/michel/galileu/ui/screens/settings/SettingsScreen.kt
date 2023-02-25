package com.michel.galileu.ui.screens.settings

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.michel.galileu.data.Data
import com.michel.galileu.models.SettingsModel

@Composable
fun SettingsScreen() {
    val data = Data.settings;

    LazyColumn(modifier = Modifier.padding(all = 4.dp)) {
        data.map { it -> item { SettingsCard(settings = it) } }
    }
}

@Composable
fun SettingsCard(settings: SettingsModel) {

    Column(modifier = Modifier
        .padding(10.dp)
        .fillMaxWidth()
        .height(30.dp)) {
        Text(text = settings.name)
    }
}
