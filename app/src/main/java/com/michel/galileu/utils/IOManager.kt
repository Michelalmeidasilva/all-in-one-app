package com.michel.galileu.utils

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import kotlinx.coroutines.*
import java.io.File
import java.io.FileOutputStream
import java.io.IOException


class IOManager() {


    fun getImage(application: Application, imageName: String?): ImageBitmap? {
        if (!imageName.isNullOrBlank()) {
            val cw = ContextWrapper(application)
            val directory = cw.getDir(".ImagesInternal", Context.MODE_PRIVATE)

            val image = BitmapFactory.decodeFile(directory.absolutePath + "/" + imageName)

            return image.asImageBitmap()
        }
        return null;
    }

    suspend fun uploadImage(
        application: Application,
        fileName: String?,
        bitmap: MutableState<Bitmap?>,
    ) {
        withContext(Dispatchers.IO) {
            val cw = ContextWrapper(application)

            val directory = cw.getDir(".ImagesInternal", Context.MODE_PRIVATE)

            val mypath = File(directory, fileName)

Ï            var fos: FileOutputStream? = null

            try {
                fos = FileOutputStream(mypath)
                // Use the compress method on the BitMap object to write image to the OutputStream
                bitmap.value?.compress(Bitmap.CompressFormat.JPEG, 100, fos)
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                try {
                    fos?.close()
                    Log.i("WriteManager", "Sucessfully")
                } catch (e: IOException) {
                    e.printStackTrace()
                }
            }

            directory.absolutePath
        }
    }


}