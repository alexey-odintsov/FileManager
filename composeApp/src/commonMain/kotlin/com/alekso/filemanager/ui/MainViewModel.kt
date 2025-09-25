package com.alekso.filemanager.ui

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.alekso.filemanager.Platform
import com.alekso.filemanager.files.FilesProvider
import com.alekso.filemanager.model.FileEntry
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.IO
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    platform: Platform,
    filesProvider: FilesProvider
) : ViewModel() {
    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _currentDirectory = mutableStateListOf<FileEntry>()
    val currentDirectory: SnapshotStateList<FileEntry> = _currentDirectory

    init {
        viewModelScope.launch(Dispatchers.IO) {
            val initialDirectory = filesProvider.getFiles(platform.getHomeDirectory())

            withContext(Main) {
                _currentDirectory.clear()
                _currentDirectory.addAll(initialDirectory)
            }
        }
    }
}