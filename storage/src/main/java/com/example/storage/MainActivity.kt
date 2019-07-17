package com.example.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val app = application as PhotoApp

        val appPrefs = getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val actPrefs = getPreferences(Context.MODE_PRIVATE)

        btShRd.setOnClickListener {
            //            readFromSharedPrefs(appPrefs)
//            readFromInternalStorage()
//            readFromPrivateExternalStorage()
//            readFromPublicExternalStorage()

            Thread {
                app.db.photoDao().allPhotos().forEach { ph ->
                    Log.i("@codekul", "Photo credits to ${ph.pc}")
                }
            }.start()
        }

        btShWr.setOnClickListener {
            //            writeToSharedPrefs(appPrefs)
//            writeToInternalStorage()
//            writeToPrivateExternalStorage()
//            writeToPublicExternalStorage()

            Thread {
                app.db.photoDao().create(
                        MyPhoto(1, "Pic at office", "Walkig", "me")
                )
            }.start()
        }
    }

    private fun writeToSharedPrefs(prefs: SharedPreferences) {
        val editor = prefs.edit()
        editor.putInt("top_score", 86)
        editor.putString("str", "android")
        editor.putBoolean("bool", true)
        editor.putFloat("flt", 89.65f)
        editor.apply()
    }

    private fun readFromSharedPrefs(prefs: SharedPreferences) {
        val prefsDt = """
                Boolean ${prefs.getBoolean("bool", false)}
                Int ${prefs.getInt("top_score", -1)}
                String ${prefs.getString("str", "codekul")}
        """.trimIndent()

        Log.i("@codekul", prefsDt)
    }

    private fun writeToInternalStorage() {
        val file = File(filesDir, "my.txt")
        Log.i("@codekul", "Path to internal storage ${file.absolutePath}")
        file.bufferedWriter().use { bw -> bw.write("This is my data") }
    }

    private fun readFromInternalStorage() {
        val file = File(filesDir, "my.txt")
        val data = file.readText()
        Log.i("@codekul" , "Data available in file is $data")
    }

    private fun writeToPrivateExternalStorage() {
        val rootFile = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)

        val file = File(rootFile, "my.txt")
        Log.i("@codekul", "Path to private external storage is ${file.absolutePath}")
        file.bufferedWriter().use { bw -> bw.write("This data to external storage") }
    }

    private fun readFromPrivateExternalStorage() {
        val rootFile = getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS)
        val file = File(rootFile, "my.txt")

        val data = file.readText()
        Log.i("@codekul", "Private External File Data is $data")
    }

    private fun writeToPublicExternalStorage() {
        val rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(rootFile, "my.txt")
        Log.i("@codekul", "Public External Storage Is - ${file.absolutePath}")

        file.bufferedWriter().use { bw -> bw.write("This is external public storage") }
    }

    private fun readFromPublicExternalStorage(){
        val rootFile = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)

        val file = File(rootFile, "my.txt")
        val data = file.readText()
        Log.i("@codekul", "External Storage Data is - $data")

    }

}