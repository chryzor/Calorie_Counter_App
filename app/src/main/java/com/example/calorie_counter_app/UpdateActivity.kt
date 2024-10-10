package com.example.calorie_counter_app

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class UpdateActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var itemType = intent.getStringExtra("ITEM_NAME")
        var quantityString = intent.getStringExtra("QUANTITY")

        var quantityInt = 0
        if (quantityString != null) {
            quantityInt = quantityString.toInt()
        }

        val quantityText : TextView = findViewById(R.id.itemQuantity)
        if (itemType == "Water") { quantityText.setText("$quantityString cups") } else { quantityText.setText("$quantityString") }

        val addButton : Button = findViewById(R.id.addButton)
        val subtractButton : Button = findViewById(R.id.subtractButton)
        addButton.setOnClickListener {
            if (itemType == "Water") {
                quantityInt += 1
                quantityText.setText("$quantityInt cups")
            } else {
                quantityInt += 10
                quantityText.setText("$quantityInt")
            }
        }
        subtractButton.setOnClickListener {
            if (itemType == "Water") {
                quantityInt -= 1
                quantityText.setText("$quantityInt cups")
            } else {
                quantityInt -= 10
                quantityText.setText("$quantityInt")
            }
        }

        val itemText : TextView = findViewById(R.id.itemName)
        itemText.setText(itemType)


        val updateButton : Button = findViewById(R.id.updateButton)
        updateButton.setOnClickListener {
            val returnIntent = Intent()
            returnIntent.putExtra("ITEM_NAME", itemType)
            returnIntent.putExtra("QUANTITY", quantityInt.toString())
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }
}