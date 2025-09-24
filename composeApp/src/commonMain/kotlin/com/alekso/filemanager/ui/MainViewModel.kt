package com.alekso.filemanager.ui

import androidx.lifecycle.ViewModel
import com.alekso.filemanager.files.FilesProvider
import com.alekso.filemanager.Platform
import com.alekso.filemanager.model.FileEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    platform: Platform,
    filesProvider: FilesProvider
) : ViewModel() {
    private val _currentDirectory = MutableStateFlow<List<FileEntry>>(filesProvider.getFiles(platform.getHomeDirectory()))
    val currentDirectory: StateFlow<List<FileEntry>> = _currentDirectory.asStateFlow()
}