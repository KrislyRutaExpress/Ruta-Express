package com.example.rutaexpress

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import datamanager.MemoryDataManager

class RideListActivity : AppCompatActivity() {

    private lateinit var rvRides: RecyclerView
    private val dataManager = MemoryDataManager.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ride_list)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        rvRides = findViewById(R.id.rvRides)
        rvRides.layoutManager = LinearLayoutManager(this)

        loadRides()

        val btnAddNewRide = findViewById<Button>(R.id.btnAddNewRide)
        btnAddNewRide.setOnClickListener {
            val intent = Intent(this, AddRideActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        loadRides()
    }

    private fun loadRides() {
        val rides = dataManager.getAllRides()
        val adapter = RideAdapter(rides)
        rvRides.adapter = adapter
    }
}
