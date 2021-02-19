package com.neyogiry.android.sample.util

import android.util.Log
import com.neyogiry.android.sample.BuildConfig

object LoggerUtil {

    private const val TAG = "LoggerUtil"

    const val VERBOSE = Log.VERBOSE
    const val DEBUG = Log.DEBUG
    const val INFO = Log.INFO
    const val WARN = Log.WARN
    const val ERROR = Log.ERROR
    const val ASSERT = Log.ASSERT

    private fun isDebug(): Boolean {
        return BuildConfig.DEBUG
    }

    fun log(message: String) {
        log(INFO, TAG, message)
    }

    fun log(tag: String, message: String) {
        log(INFO, tag, message)
    }

    fun log(priority: Int, message: String) {
        log(priority, TAG, message)
    }

    fun log(priority: Int, tag: String, message: String) {
        if(!isDebug()) return

        Log.println(priority, tag, message)
    }

    /**
     * Priorities
     */
    fun v(tag: String, message: String) {
        log(VERBOSE, tag, message)
    }

    fun i(tag: String, message: String) {
        log(INFO, tag, message)
    }

    fun w(tag: String, message: String) {
        log(WARN, tag, message)
    }

    fun e(tag: String, message: String) {
        log(ERROR, tag, message)
    }

}