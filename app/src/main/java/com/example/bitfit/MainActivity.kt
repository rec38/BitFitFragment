package com.example.bitfit

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    private val waterList = mutableListOf<WaterEntity>()
    private lateinit var waterAdapter: WaterAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val waterRv = findViewById<RecyclerView>(R.id.waterRv)
        val addBtn = findViewById<Button>(R.id.addBtn)

        waterAdapter = WaterAdapter(waterList)

        waterRv.adapter = waterAdapter
        waterRv.layoutManager = LinearLayoutManager(this)

        addBtn.setOnClickListener {
            val intent = Intent(this, DetailActivity::class.java)
            startActivity(intent)
        }

        val db = AppDatabase.getDatabase(applicationContext)

        lifecycleScope.launch {
            db.waterDao().getAllWaterEntries().collectLatest { list ->
                waterList.clear()
                waterList.addAll(list)
                waterAdapter.notifyDataSetChanged()
            }
        }
    }
}
