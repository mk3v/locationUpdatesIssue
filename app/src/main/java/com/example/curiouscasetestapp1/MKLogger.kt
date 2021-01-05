package com.example.curiouscasetestapp1

import android.annotation.SuppressLint
import android.util.Log
import java.io.FileWriter
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

object MKLogger {
    @SuppressLint("SimpleDateFormat")
    val fileNameSuffix: String = SimpleDateFormat("yyyyMMddHHmm'.txt'").format(Date())

    fun log(_message: String) {
        val message = "${getCurrentTime()} =>\t $_message}"
        Log.i("_MK_ ", message)
        val fileName = "CuriosCase_Test_$fileNameSuffix.txt"
        writeToFile(message, fileName)
    }

    fun logLoc(_message: String) {
        val message = "${getCurrentTime()} =>\t $_message}"
        Log.i("_MK_", message)
        val fileName = "CuriosCase_LocationLogs_$fileNameSuffix.txt"
        writeToFile(message, fileName)
    }

    private fun writeToFile(message: String, fileName: String) {
        try {
            val fullFileName = "${ApplicationContextProvider.getContext()!!.filesDir}/$fileName"
            val fw = FileWriter(fullFileName, true) //the true will append the new data
            fw.write("$message\n")
            fw.close()
        } catch (ioe: IOException) {
            Log.e("_MK_", "Error while writing to file: $ioe")
        } finally {

        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun getCurrentTime(): String {
        return SimpleDateFormat("HH_mm_ss").format(Date())
    }
}
