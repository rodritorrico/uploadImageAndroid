package com.pikachu.uploadimage

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File


class MainActivity : AppCompatActivity() {

    private lateinit var storageRef: StorageReference
    private var REQUEST_CODE = 43;
    private lateinit var path: String
    private lateinit var fileName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        storageRef = FirebaseStorage.getInstance().reference;


        uploadButton.setOnClickListener {
            startSearch()
        }

        confirmButton.setOnClickListener {
            uploadImage(path,"panchito.jpg")

        }
    }

    private fun uploadImage(pathName: String, fileName: String) {

        var file = File(pathName)
        var fileUri = Uri.fromFile(file)

        println(file)


        val fileRef: StorageReference = storageRef.child("pets/panchito.jpg")

        fileRef.putFile(fileUri)
            .addOnSuccessListener { taskSnapshot -> // Get a URL to the uploaded content
                val downloadUrl = taskSnapshot.storage.downloadUrl
            }
            .addOnFailureListener {
                println("error")
            }
    }

    private fun startSearch() {
        val intent =  Intent (Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        startActivityForResult(intent, REQUEST_CODE)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                var uri = data.data
                println(uri?.path)
                path = uri?.path!!
                filePath.text = uri?.path
            }
        }


    }
}