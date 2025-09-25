package com.alekso.filemanager.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import com.alekso.filemanager.model.FileEntry

@Composable
actual fun FilesPanels(
    currentDirectory: SnapshotStateList<FileEntry>,
    selectedFile: FileEntry?,
    callbacks: FilesCallbacks
) {
}