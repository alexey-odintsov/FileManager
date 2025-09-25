package com.alekso.filemanager

import com.alekso.filemanager.files.FilesProvider
import com.alekso.filemanager.model.FileEntry

class IOSFileProvider: FilesProvider {
    override suspend fun getFiles(directory: String): List<FileEntry> {
        TODO("Not yet implemented")
    }
}