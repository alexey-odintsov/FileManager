package com.alekso.filemanager

import android.os.Build
import com.alekso.filemanager.files.AndroidFilesProvider
import com.alekso.filemanager.files.FilesProvider

class AndroidPlatform : Platform {
    override val name: String = "Android ${Build.VERSION.SDK_INT}"
    override fun getHomeDirectory(): String = "/sdcard/"
    override fun getFilesProvider(): FilesProvider = AndroidFilesProvider()
}

actual fun getPlatform(): Platform = AndroidPlatform()