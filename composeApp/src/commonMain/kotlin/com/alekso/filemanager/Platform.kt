package com.alekso.filemanager

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform