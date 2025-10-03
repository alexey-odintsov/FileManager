package com.alekso.filemanager.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.alekso.filemanager.model.FileEntry
import filemanager.composeapp.generated.resources.Res
import filemanager.composeapp.generated.resources.directory
import filemanager.composeapp.generated.resources.file
import org.jetbrains.compose.resources.painterResource

@Composable
actual fun FileItem(i: Int, fileEntry: FileEntry, isSelected: Boolean, callbacks: FilesCallbacks) {
    val bgColor = when {
        isSelected -> Color(56, 99, 222)
        i % 2 == 0 -> Color(242, 243, 243)
        else -> Color.White
    }
    Row(
        Modifier.fillMaxWidth().background(bgColor)
            .combinedClickable(
                onClick = { callbacks.onFileClicked(fileEntry) }
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier.size(20.dp, 20.dp).padding(top = 4.dp),
            painter = painterResource(if (fileEntry.isDirectory) Res.drawable.directory else Res.drawable.file),
            contentDescription = null,
        )
        Text(
            modifier = Modifier.padding(horizontal = 4.dp).weight(1f).fillMaxWidth(),
            text = fileEntry.name,
            color = if (isSelected) Color.White else MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}