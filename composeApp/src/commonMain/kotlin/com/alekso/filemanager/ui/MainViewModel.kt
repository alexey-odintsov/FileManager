package com.alekso.filemanager.ui

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
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
    private val filesProvider: FilesProvider
) : ViewModel() {

    val callbacks = object : FilesCallbacks {
        override fun onFileClicked(fileEntry: FileEntry) = onFileClickedInternal(fileEntry)
    }

    private val viewModelJob = SupervisorJob()
    private val viewModelScope = CoroutineScope(Main + viewModelJob)

    private val _selectedFile = mutableStateOf<FileEntry?>(null)
    val selectedFile: State<FileEntry?> = _selectedFile

    private val _selectedFileContent = mutableStateOf<String?>(null)
    val selectedFileContent: State<String?> = _selectedFileContent

    private val _currentDirectory = mutableStateListOf<FileEntry>()
    val currentDirectory: SnapshotStateList<FileEntry> = _currentDirectory

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadDirectory(platform.getHomeDirectory())
        }
    }

    private fun openDirectory(fileEntry: FileEntry) {
        viewModelScope.launch(Dispatchers.IO) {
            if (fileEntry.isDirectory) {
                loadDirectory(fileEntry.path)
            } else {
                selectFile(fileEntry)
            }
        }
    }

    private fun onFileClickedInternal(fileEntry: FileEntry) {
        if (_selectedFile.value == fileEntry && fileEntry.isDirectory) {
            openDirectory(fileEntry)
        } else {
            selectFile(fileEntry)
        }
    }

    private fun selectFile(fileEntry: FileEntry) {
        _selectedFile.value = fileEntry
        if (!fileEntry.isDirectory) {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    loadFileContent(fileEntry.path)
                }
            }
        }
    }

    private suspend fun loadDirectory(path: String) {
        val initialDirectory = filesProvider.getFiles(path)

        withContext(Main) {
            _currentDirectory.clear()
            _currentDirectory.addAll(initialDirectory)
        }
    }

    private suspend fun loadFileContent(path: String) {
        println("loadFileContent($path)")
        _selectedFileContent.value = filesProvider.getFileContent(path)
    }

}