package com.example.calorie_counter_app

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var dateValue: TextView
    private lateinit var mealsValue: TextView
    private lateinit var waterValue: TextView
    private lateinit var snacksValue: TextView
    private lateinit var totalValue: TextView

    private lateinit var mealsUpdateButton: Button
    private lateinit var waterUpdateButton: Button
    private lateinit var snacksUpdateButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views by ID
        dateValue = findViewById(R.id.dateValue)
        mealsValue = findViewById(R.id.mealsValue)
        waterValue = findViewById(R.id.waterValue)
        snacksValue = findViewById(R.id.snacksValue)
        totalValue = findViewById(R.id.totalValue)

        mealsUpdateButton = findViewById(R.id.mealsUpdateButton)
        waterUpdateButton = findViewById(R.id.waterUpdateButton)
        snacksUpdateButton = findViewById(R.id.snacksUpdateButton)

    }
}