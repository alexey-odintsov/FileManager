package com.alekso.filemanager.ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.alekso.filemanager.model.FileEntry

@Composable
fun FileItem(fileEntry: FileEntry) {
    Text(fileEntry.name)
}