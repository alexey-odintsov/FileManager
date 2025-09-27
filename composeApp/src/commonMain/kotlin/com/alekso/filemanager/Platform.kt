package com.alekso.filemanager

import com.alekso.filemanager.files.FilesProvider

interface Platform {
    val name: String
    fun getHomeDirectory(): String
    fun getFilesProvider(): FilesProvider
}

expect fun getPlatform(): Platform