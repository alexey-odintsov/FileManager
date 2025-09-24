package com.alekso.filemanager.model

data class FileEntry(
    val name: String,
    val path: String, // absolute path
    val isDirectory: Boolean,
    val size: Long,
    val createdDate: Long = 0L,
) {
    fun getParentDirectory(): FileEntry {
        val parentName = path.substringBeforeLast(name)
        return FileEntry("${path}/..", parentName, isDirectory = true, size = 0)
    }
}
