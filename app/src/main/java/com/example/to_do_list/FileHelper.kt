package com.example.to_do_list

import android.content.Context
import java.io.FileNotFoundException
import java.io.IOException
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

object FileHelper {
    const val FILENAME = "listinfo.dat"
    fun writeData(item: ArrayList<String?>?, context: Context) {
        try {
            val fos = context.openFileOutput(FILENAME, Context.MODE_PRIVATE)
            val oas = ObjectOutputStream(fos)
            oas.writeObject(item)
            oas.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun readData(context: Context): ArrayList<String?>? {
        var itemList: ArrayList<String?>? = null
        try {
            val fis = context.openFileInput(FILENAME)
            val ois = ObjectInputStream(fis)
            itemList = ois.readObject() as ArrayList<String?>
        } catch (e: FileNotFoundException) {
            itemList = ArrayList()
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ClassNotFoundException) {
            e.printStackTrace()
        }
        return itemList
    }
}