package com.example.bitfitFragment

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val amountEt = findViewById<EditText>(R.id.amountEt)
        val dateEt = findViewById<EditText>(R.id.dateEt)
        val saveBtn = findViewById<Button>(R.id.saveBtn)

        val db = AppDatabase.getDatabase(applicationContext)

        saveBtn.setOnClickListener {
            val amountText = amountEt.text.toString().trim()
            val date = dateEt.text.toString().trim()

            val amount = amountText.toIntOrNull()

            if (amount == null || amount <= 0) {
                amountEt.error = "Enter a valid positive amount"
                return@setOnClickListener
            }

            if (date.isEmpty()) {
                dateEt.error = "Enter a date"
                return@setOnClickListener
            }

            lifecycleScope.launch {
                db.waterDao().insertWaterEntry(
                    WaterEntity(
                        waterAmount = amount,
                        date = date
                    )
                )

                Toast.makeText(
                    this@DetailActivity,
                    "Water entry saved",
                    Toast.LENGTH_SHORT
                ).show()

                finish()
            }
        }
    }
}
