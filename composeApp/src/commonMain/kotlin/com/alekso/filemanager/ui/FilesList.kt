package com.alekso.filemanager.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.alekso.filemanager.model.FileEntry

@Composable
fun FilesList(files: SnapshotStateList<FileEntry>, callbacks: FilesCallbacks) {
    Column {
        val state = rememberLazyListState()

        LazyColumn(
            modifier = Modifier.fillMaxWidth(),
            state = state,
        ) {
            itemsIndexed(
                items = files,
                key = { _, file -> file.path },
                contentType = { _, _ -> FileEntry::class },
            ) { i, file ->
                FileItem(i, file, false, callbacks)
            }
        }
    }
}