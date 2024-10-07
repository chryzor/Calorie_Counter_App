package com.example.calorie_counter_app

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {
    private lateinit var dateValue: EditText
    private lateinit var mealsValue: TextView
    private lateinit var waterValue: TextView
    private lateinit var snacksValue: TextView
    private lateinit var totalValue: TextView

    private lateinit var mealsUpdateButton: Button
    private lateinit var waterUpdateButton: Button
    private lateinit var snacksUpdateButton: Button
    @RequiresApi(Build.VERSION_CODES.O)
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

        mealsUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Meals")
            startActivity(intent)
        }
        waterUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Water")
            startActivity(intent)
        }
        snacksUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Snacks")
            startActivity(intent)
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val formattedDate = current.format(formatter)
        dateValue.setText(formattedDate)





    }
}