package com.alekso.filemanager.ui

import androidx.compose.foundation.VerticalScrollbar
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.rememberScrollbarAdapter
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.alekso.filemanager.model.FileEntry
import org.jetbrains.compose.splitpane.ExperimentalSplitPaneApi
import org.jetbrains.compose.splitpane.HorizontalSplitPane
import org.jetbrains.compose.splitpane.rememberSplitPaneState

@OptIn(ExperimentalSplitPaneApi::class)
@Composable
actual fun FilesPanels(
    currentDirectory: SnapshotStateList<FileEntry>,
    selectedFile: FileEntry?,
    selectedFileContent: String?,
    callbacks: FilesCallbacks
) {
    val splitterState = rememberSplitPaneState(0.5f)

    HorizontalSplitPane(
        splitPaneState = splitterState
    ) {
        first(20.dp) {
            FilesList(currentDirectory, selectedFile, callbacks)
        }
        second(20.dp) {
            val scrollState = rememberScrollState()
            Box {
                Column(Modifier.verticalScroll(scrollState)) {
                    SelectionContainer {
                        Text(selectedFileContent.toString())
                    }
                }
                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(
                        scrollState = scrollState
                    )
                )
            }
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