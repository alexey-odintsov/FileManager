package com.alekso.filemanager.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alekso.filemanager.model.FileEntry
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
actual fun FilesPanels(currentDirectory: SnapshotStateList<FileEntry>, callbacks: FilesCallbacks) {
    val splitterState = rememberSplitPaneState(0.5f)

    HorizontalSplitPane(
        splitPaneState = splitterState
    ) {
        first(20.dp) {
            FilesList(currentDirectory, callbacks)
        }
        second(20.dp) {
            FilesList(currentDirectory, callbacks)
        }
        splitter {
            visiblePart {
                Box(
                    Modifier
                        .width(1.dp)
                        .fillMaxHeight()
                        .background(MaterialTheme.colors.onSurface.copy(alpha = 0.5f))
                )
            }

            handle {
                Box(
                    Modifier
                        .markAsHandle()
                        .width(4.dp)
                        .fillMaxHeight()
                )
            }
        }
    }
}