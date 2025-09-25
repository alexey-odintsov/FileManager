package com.alekso.filemanager.ui

import androidx.compose.runtime.Composable
import com.alekso.filemanager.model.FileEntry

interface FilesCallbacks {
    fun onFileClicked(fileEntry: FileEntry)
    fun onFileSelected(fileEntry: FileEntry)
}

@Composable
expect fun FileItem(i: Int, fileEntry: FileEntry, isSelected: Boolean, callbacks: FilesCallbacks)