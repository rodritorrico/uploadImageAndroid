package com.pikachu.uploadimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {


    private val PICK_IMAGE_REQUEST = 1
    private var filePath: Uri? = null
    private var firebaseStorage: FirebaseStorage? = null
    private var storageReference: StorageReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        firebaseStorage = FirebaseStorage.getInstance()
        storageReference = FirebaseStorage.getInstance().reference
        uploadButton.setOnClickListener { ImagePicker() }
        confirmButton.setOnClickListener { uploadImage() }


    }


    private fun ImagePicker() {
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK) {
            if (data == null || data.data == null) {
                return
            }
            filePath = data.data
            try {
                imagePreview.setImageURI(null);
                imagePreview.setImageURI(filePath)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    private fun uploadImage() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    private fun upload(pathName: String, fileName: String) {
//
//        var file = File(pathName)
//        var fileUri = Uri.fromFile(file)
//
//        println(file)
//
//
//        val fileRef: StorageReference = firebaseStorage
//
//        fileRef.putFile(fileUri)
//            .addOnSuccessListener { taskSnapshot -> // Get a URL to the uploaded content
//                val downloadUrl = taskSnapshot.storage.downloadUrl
//            }
//            .addOnFailureListener {
//                println("error")
//            }
    }

}