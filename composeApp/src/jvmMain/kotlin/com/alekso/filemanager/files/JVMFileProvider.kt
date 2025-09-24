package com.alekso.filemanager.files

import com.alekso.filemanager.model.FileEntry
import java.io.File

class JVMFileProvider: FilesProvider {
    override fun getFiles(directory: String): List<FileEntry> {
        val file = File(directory)
        if (file.exists()) {
            val files = file.listFiles()?.map { f ->
                FileEntry(
                    name = f.name,
                    path = f.absolutePath,
                    isDirectory = f.isDirectory,
                    size = f.length(),
                    createdDate = f.lastModified()
                )
            }?.sortedWith(
                Comparator
                    .comparing(FileEntry::isDirectory).reversed()
                    .thenComparing(FileEntry::path)
            )?.toMutableList() ?: mutableListOf()
            files.add(
                0,
                FileEntry(
                    name = file.name,
                    path = file.absolutePath,
                    isDirectory = file.isDirectory,
                    size = file.length()
                ).getParentDirectory()
            )
            return files
        } else {
            println("File $file doesn't exist!")
            return emptyList()
        }
    }
}