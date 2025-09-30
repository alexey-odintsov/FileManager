package com.alekso.filemanager.files

import com.alekso.filemanager.model.FileEntry

interface FilesProvider {
    suspend fun getFiles(directory: String): List<FileEntry>
    suspend fun getFileContent(path: String): String
}