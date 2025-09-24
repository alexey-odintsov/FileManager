package com.alekso.filemanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.alekso.filemanager.getPlatform

@Composable
fun MainScreen() {
    val platform = remember { getPlatform() }
    val viewModel: MainViewModel = viewModel { MainViewModel(platform, platform.getFilesProvider()) }

    Column {
        Text(platform.name)
        FilesList(viewModel.currentDirectory.value)
    }
}