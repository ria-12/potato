package com.example.kakaomapapi

import android.content.Context
import android.os.Binder
import android.util.Log
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.logging.FileHandler
import java.util.logging.Formatter
import java.util.logging.Level
import java.util.logging.LogRecord
import java.util.logging.Logger

class LogWrapper {

    companion object {
        private const val LOG_FILE_SIZE_LIMIT = 512 * 1024
        private const val LOG_FILE_MAX_COUNT = 2
        private const val LOG_FILE_NAME = "log"
        private val dateformat = SimpleDateFormat("MM-dd HH:mm:ss.SSS: ", Locale.getDefault())
        private val date : Date by lazy { Date() }
        private var logger : Logger? = null
        private var fileHandler : FileHandler? = null

        fun writeLog(ctx: Context) {
            try {
                fileHandler = FileHandler(ctx.filesDir.absolutePath + File.separator +
                        LOG_FILE_NAME, LOG_FILE_SIZE_LIMIT, LOG_FILE_MAX_COUNT, true)

                fileHandler?.formatter = object : Formatter() {
                    override fun format(r: LogRecord): String {
                        date.time = System.currentTimeMillis()
                        val sb = java.lang.StringBuilder(80)
                            .append(dateformat.format(date))
                            .append(r.message)
                        return sb.toString()
                    }
                }

//                logger = Logger.getLogger(LogWrapper.javaClass.name)
                logger?.let {
                    Log.d("times", date.time.toString())
                    it.addHandler(fileHandler)
                    it.level = Level.ALL
                    it.useParentHandlers = false
                    }
            }catch (e: IOException) { e.printStackTrace() }
        }

        fun d(ctx: Context, tag: String, msg: String) {
            if (logger != null) {
                logger?.log(Level.INFO, String.format(" D/%s(%d): %s\n", tag, Binder.getCallingPid(), msg))
            }
            Log.d(tag,msg)
            writeLog(ctx)
        }

        fun v(ctx: Context, tag: String, msg: String) {
            if (logger != null) {
                logger?.log(Level.INFO, String.format(" V/%s(%d): %s\n", tag, Binder.getCallingPid(), msg))
            }
            Log.v(tag,msg)
            writeLog(ctx)
        }
    }

}