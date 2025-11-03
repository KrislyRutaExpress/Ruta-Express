package com.example.rutaexpress

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import datamanager.MemoryDataManager
import entity.User
import java.util.UUID

class Register : AppCompatActivity() {

    private lateinit var txtName: EditText
    private lateinit var txtEmail: EditText
    private lateinit var txtPassword: EditText
    private val dataManager = MemoryDataManager()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_register)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        txtName = findViewById(R.id.nameinsert)
        txtEmail = findViewById(R.id.emailinsertregist)
        txtPassword = findViewById(R.id.passinsertregist)

        val btnRegister = findViewById<Button>(R.id.btnRegister)
        btnRegister.setOnClickListener(View.OnClickListener {
            saveUser()
        })
    }

    private fun validationData(): Boolean {
        return txtName.text.isNotEmpty() &&
                txtEmail.text.isNotEmpty() &&
                txtPassword.text.isNotEmpty()
    }

    private fun saveUser() {
        try {
            if (validationData()) {
                val user = User(
                    id = UUID.randomUUID().toString(),
                    name = txtName.text.toString(),
                    email = txtEmail.text.toString(),
                    password = txtPassword.text.toString()
                )

                dataManager.addUser(user)

                Toast.makeText(this, "Registration successful!", Toast.LENGTH_LONG).show()

                // Navegar al MainActivity
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Please fill all fields correctly", Toast.LENGTH_SHORT).show()
            }
        } catch (e: Exception) {
            Toast.makeText(this, e.message.toString(), Toast.LENGTH_LONG).show()
        }
    }
}

    }