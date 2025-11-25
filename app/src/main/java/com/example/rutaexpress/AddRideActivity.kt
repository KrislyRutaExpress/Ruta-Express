package com.example.rutaexpress

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import datamanager.MemoryDataManager
import entity.Ride
import java.util.UUID

class AddRideActivity : AppCompatActivity() {

    private lateinit var imgRidePhoto: ImageView
    private lateinit var txtRestaurantName: EditText
    private lateinit var txtCustomerAddress: EditText
    private var selectedBitmap: Bitmap? = null
    private val dataManager = MemoryDataManager.getInstance()

    private val cameraLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageBitmap = result.data?.extras?.get("data") as? Bitmap
            if (imageBitmap != null) {
                selectedBitmap = imageBitmap
                imgRidePhoto.setImageBitmap(imageBitmap)
            }
        }
    }

    private val galleryLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val imageUri = result.data?.data
            if (imageUri != null) {
                try {
                    val bitmap = MediaStore.Images.Media.getBitmap(contentResolver, imageUri)
                    selectedBitmap = bitmap
                    imgRidePhoto.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "Error loading image", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            openCamera()
        } else {
            Toast.makeText(this, "Camera permission denied", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_ride)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        imgRidePhoto = findViewById(R.id.imgRidePhoto)
        txtRestaurantName = findViewById(R.id.txtRestaurantName)
        txtCustomerAddress = findViewById(R.id.txtCustomerAddress)

        val btnCamera = findViewById<Button>(R.id.btnCamera)
        val btnGallery = findViewById<Button>(R.id.btnGallery)
        val btnSaveRide = findViewById<Button>(R.id.btnSaveRide)

        btnCamera.setOnClickListener {
            checkCameraPermissionAndOpen()
        }

        btnGallery.setOnClickListener {
            openGallery()
        }

        btnSaveRide.setOnClickListener {
            saveRide()
        }
    }

    private fun checkCameraPermissionAndOpen() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                openCamera()
            }
            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> {
                Toast.makeText(this, "Camera permission is needed to take photos", Toast.LENGTH_LONG).show()
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
            else -> {
                requestPermissionLauncher.launch(Manifest.permission.CAMERA)
            }
        }
    }

    private fun openCamera() {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraLauncher.launch(cameraIntent)
    }

    private fun openGallery() {
        val galleryIntent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        galleryLauncher.launch(galleryIntent)
    }

    private fun validateData(): Boolean {
        return txtRestaurantName.text.isNotEmpty() &&
                txtCustomerAddress.text.isNotEmpty()
    }

    private fun saveRide() {
        try {
            if (validateData()) {
                val ride = Ride(
                    id = UUID.randomUUID().toString(),
                    restaurantName = txtRestaurantName.text.toString(),
                    customerAddress = txtCustomerAddress.text.toString(),
                    status = "pending",
                    deliveryPersonId = null,
                    photo = selectedBitmap
                )

                dataManager.addRide(ride)

                Toast.makeText(this, "Ride saved successfully!", Toast.LENGTH_LONG).show()

                val intent = Intent(this, RideListActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all required fields", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}
