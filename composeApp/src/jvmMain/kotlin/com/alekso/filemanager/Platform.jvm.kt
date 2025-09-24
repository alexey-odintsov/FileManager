package com.alekso.filemanager

import com.alekso.filemanager.files.FilesProvider
import com.alekso.filemanager.files.JVMFileProvider

class JVMPlatform: Platform {
    override val name: String = "Java ${System.getProperty("java.version")}"
    override fun getHomeDirectory(): String = System.getProperty("user.home")
    override fun getFilesProvider(): FilesProvider = JVMFileProvider()
}

actual fun getPlatform(): Platform = JVMPlatform()