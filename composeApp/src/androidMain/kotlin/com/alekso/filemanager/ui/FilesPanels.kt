package com.alekso.filemanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alekso.filemanager.model.FileEntry
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.VerticalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
actual fun FilesPanels(
    currentDirectory: SnapshotStateList<FileEntry>,
    selectedFile: FileEntry?,
    selectedFileContent: String?,
    callbacks: FilesCallbacks
) {
    val splitterState = rememberSplitPaneState(0.7f)

    VerticalSplitPane(
        splitPaneState = splitterState
    ) {
        first(20.dp) {
            FilesList(currentDirectory, selectedFile, callbacks)
        }
        second(20.dp) {
            Text(selectedFile.toString())
        }
        splitter {
            visiblePart {
                Box(
                    Modifier
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.onSurface.copy(alpha = 0.5f))
                )
            }
            handle {
                Box(
                    Modifier
                        .markAsHandle()
                        .height(4.dp)
                        .fillMaxWidth()
                )
            }
        }
    }
}