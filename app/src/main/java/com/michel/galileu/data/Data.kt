package com.michel.galileu.data

import com.michel.galileu.models.SettingsModel


object Data {
    val settings: List<SettingsModel> = listOf(
        SettingsModel(name = "Privacy", "test"), SettingsModel(name = "Security", "test"),
        SettingsModel(name = "About", "test"),
    );
}
