package com.alekso.filemanager.files

import com.alekso.filemanager.model.FileEntry

interface FilesProvider {
    fun getFiles(directory: String): List<FileEntry>
}