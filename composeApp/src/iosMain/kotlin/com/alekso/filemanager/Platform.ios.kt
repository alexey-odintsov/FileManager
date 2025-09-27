package com.alekso.filemanager

import com.alekso.filemanager.files.FilesProvider
import platform.UIKit.UIDevice

class IOSPlatform: Platform {
    override val name: String = UIDevice.currentDevice.systemName() + " " + UIDevice.currentDevice.systemVersion
    override fun getHomeDirectory(): String = "~"
    override fun getFilesProvider(): FilesProvider = IOSFileProvider()
}

actual fun getPlatform(): Platform = IOSPlatform()