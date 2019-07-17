package com.example.pickimage1

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.icu.util.Calendar
import android.media.MediaScannerConnection
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import com.karumi.dexter.Dexter
import com.karumi.dexter.MultiplePermissionsReport
import com.karumi.dexter.PermissionToken
import com.karumi.dexter.listener.DexterError
import com.karumi.dexter.listener.PermissionRequestErrorListener
import com.karumi.dexter.listener.multi.MultiplePermissionsListener
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.Int as Int1

class MainActivity:AppCompatActivity() {
    private var btn: Button? = null
    private var imageview: ImageView = null
    private val GALLERY = 1
    private val CAMERA = 2
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestMultiplePermissions()
        btn = findViewById(R.id.btn) as Button
        imageview = findViewById(R.id.iv) as ImageView
        btn!!.setOnClickListener(object: View.OnClickListener() {
            override fun onClick(v:View) {
                showPictureDialog()
            }
        })
    }
    private fun showPictureDialog() {
        val pictureDialog = AlertDialog.Builder(this)
        pictureDialog.setTitle("Select Action")
        val pictureDialogItems = arrayOf<String>("Select photo from gallery", "Capture photo from camera")
        pictureDialog.setItems(pictureDialogItems,
                object: DialogInterface.OnClickListener {
                    override fun onClick(dialog:DialogInterface, which: Int1) {
                        when (which) {
                            0 -> choosePhotoFromGallary()
                            1 -> takePhotoFromCamera()
                        }
                    }
                })
        pictureDialog.show()
    }
    fun choosePhotoFromGallary() {
        val galleryIntent = Intent(Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        startActivityForResult(galleryIntent, GALLERY)
    }
    private fun takePhotoFromCamera() {
        val intent = Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(intent, CAMERA)
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun saveImage(myBitmap: Bitmap):String {
        val bytes = ByteArrayOutputStream()
        myBitmap.compress(Bitmap.CompressFormat.JPEG, 90, bytes)
        val wallpaperDirectory = File(
                Environment.getExternalStorageDirectory() + IMAGE_DIRECTORY)
        // have the object build the directory structure, if needed.
        if (!wallpaperDirectory.exists())
        {
            wallpaperDirectory.mkdirs()
        }
        try
        {
            val f = File(wallpaperDirectory, (Calendar.getInstance()
                    .getTimeInMillis() + ".jpg"))
            val createNewFile: Any = f.createNewFile()
            val fo = FileOutputStream(f).also {
                it.write(bytes.toByteArray())
            }
            MediaScannerConnection.scanFile(this,
                    arrayOf<String>(f.getPath()),
                    arrayOf<String>("image/jpeg"), null)
            fo.close()
            Log.d("TAG", "File Saved::---&gt;" + f.getAbsolutePath())
            return f.getAbsolutePath()
        }
        catch (e1:IOException) {
            e1.printStackTrace()
        }
        return ""
    }
    private fun requestMultiplePermissions() {
        Dexter.withActivity(this)
                .withPermissions(
                        Manifest.permission.CAMERA,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,
                        Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(object: MultiplePermissionsListener() {
                    fun onPermissionsChecked(report: MultiplePermissionsReport) {
                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted())
                        {
                            Toast.makeText(getApplicationContext(), "All permissions are granted by user!", Toast.LENGTH_SHORT).show()
                        }
                        // check for permanent denial of any permission
                        if (report.isAnyPermissionPermanentlyDenied())
                        {
                            // show alert dialog navigating to Settings
                            //openSettingsDialog();
                        }
                    }
                    fun onPermissionRationaleShouldBeShown(token: PermissionToken) {
                        token.continuePermissionRequest()
                    }
                }).withErrorListener(object: PermissionRequestErrorListener() {
                    fun onError(error: DexterError) {
                        Toast.makeText(getApplicationContext(), "Some Error! ", Toast.LENGTH_SHORT).show()
                    }
                })
                .onSameThread()
                .check()
    }
    companion object {
        private val IMAGE_DIRECTORY = "/demonuts"
        @RequiresApi(Build.VERSION_CODES.N)
        fun kotlin.Int.onActivityResult(mainActivity: MainActivity, resultCode: kotlin.Int, data: Intent) {
            super.onActivityResult(this, resultCode, data)
            if (resultCode == mainActivity.RESULT_CANCELED)
            {
                return
            }
            if (this == mainActivity.GALLERY)
            {
                if (data != null)
                {
                    val contentURI = data.getData()
                    try
                    {
                        val bitmap = MediaStore.Images.Media.getBitmap(mainActivity.getContentResolver(), contentURI)
                        val path = mainActivity.saveImage(bitmap)
                        Toast.makeText(mainActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
                        mainActivity.imageview.setImageBitmap(bitmap)
                    }
                    catch (e: IOException) {
                        e.printStackTrace()
                        Toast.makeText(mainActivity, "Failed!", Toast.LENGTH_SHORT).show()
                    }
                }
            }
            else if (this == mainActivity.CAMERA)
            {
                val thumbnail = data.getExtras().get("data") as Bitmap
                mainActivity.imageview.setImageBitmap(thumbnail)
                mainActivity.saveImage(thumbnail)
                Toast.makeText(mainActivity, "Image Saved!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}