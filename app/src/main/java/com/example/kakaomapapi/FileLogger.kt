package com.example.kakaomapapi

import android.content.Context
import java.io.File
import java.io.IOException

object FileLogger {
    private const val DIRECTORY_NAME = "obigoLog"
    private const val FILE_NAME = "log.txt"
    private lateinit var logDir : File

    fun writeFile(ctx: Context) {
        logDir = File("${ctx.filesDir.absolutePath}${File.separator}$DIRECTORY_NAME")
        val logFile = File("${logDir.absolutePath}${File.separator}$FILE_NAME")

        if(!logDir.exists()) makeFileDir()

        try {
            Runtime.getRuntime().exec("logcat -c")
            Runtime.getRuntime().exec("logcat -f  $logFile *:W")
        }catch (e: IOException) { print(e.printStackTrace()) }
    }

    private fun makeFileDir() {
        logDir.mkdirs()
    }
}