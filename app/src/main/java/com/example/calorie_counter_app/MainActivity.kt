package com.example.calorie_counter_app

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
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
        var actualWaterValue = 3
        snacksValue = findViewById(R.id.snacksValue)
        totalValue = findViewById(R.id.totalValue)

        mealsUpdateButton = findViewById(R.id.mealsUpdateButton)
        waterUpdateButton = findViewById(R.id.waterUpdateButton)
        snacksUpdateButton = findViewById(R.id.snacksUpdateButton)

        var itemType = ""

        mealsUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Meals")
            itemType = "Meals"
            intent.putExtra("QUANTITY", mealsValue.text.toString())
            activity2launcher.launch(intent)
        }
        waterUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Water")
            itemType = "Water"
            intent.putExtra("QUANTITY", actualWaterValue.toString())
            activity2launcher.launch(intent)
        }
        snacksUpdateButton.setOnClickListener {
            val intent = Intent(this, UpdateActivity::class.java)
            intent.putExtra("ITEM_NAME", "Snacks")
            itemType = "Snacks"
            intent.putExtra("QUANTITY", snacksValue.text.toString())
            activity2launcher.launch(intent)
        }

        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
        val formattedDate = current.format(formatter)
        dateValue.setText(formattedDate)

    }

    private val activity2launcher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            var quantityResult = result.data?.getStringExtra("QUANTITY")
            var itemNameResult = result.data?.getStringExtra("ITEM_NAME")
            val resultQuantityInt = quantityResult?.toIntOrNull()
            if (resultQuantityInt != null) {
                when (itemNameResult) {
                    "Water" -> waterValue.setText("$resultQuantityInt cups")
                    "Meals" -> mealsValue.setText("$resultQuantityInt")
                    "Snacks" -> snacksValue.setText("$resultQuantityInt")
                }
            } else {
                Log.e("MainActivity", "Invalid quantity received")
            }
            val totalMealString = mealsValue.text.toString()
            val totalMealInt = totalMealString.toInt()
            val totalSnacksString = snacksValue.text.toString()
            val totalSnacksInt = totalSnacksString.toInt()
            var totalCalories = totalMealInt + totalSnacksInt

            if (totalCalories != null) {
                totalValue.setText(totalCalories.toString())
            } else {
                totalValue.setText("Invalid input")
            }
        }

    }

//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
//            val resultName = data?.getStringExtra("ITEM_NAME")
//            val resultQuantityString = data?.getStringExtra("QUANTITY")
//            val resultQuantityInt = resultQuantityString?.toIntOrNull()
//            if (resultQuantityInt != null) {
//                when (resultName) {
//                    "Water" -> waterValue.setText("$resultQuantityInt")
//                    "Meals" -> mealsValue.setText("$resultQuantityInt")
//                    "Snacks" -> snacksValue.setText("$resultQuantityInt")
//                }
//            } else {
//                Log.e("MainActivity", "Invalid quantity received")
//            }
//        }
//    }
}