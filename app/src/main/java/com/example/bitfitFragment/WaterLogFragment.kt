package com.example.bitfitFragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.jvm.java

class WaterLogFragment : Fragment() {

    private val waterList = mutableListOf<WaterEntity>()
    private lateinit var waterAdapter: WaterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_water_log,
            container,
            false
        )
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        val waterRv =
            view.findViewById<RecyclerView>(R.id.waterRv)

        val addBtn =
            view.findViewById<Button>(R.id.addBtn)

        waterAdapter = WaterAdapter(waterList)

        waterRv.layoutManager =
            LinearLayoutManager(requireContext())
        waterRv.adapter = waterAdapter

        addBtn.setOnClickListener {
            val intent = Intent(
                requireContext(),
                DetailActivity::class.java
            )
            startActivity(intent)
        }

        val db = AppDatabase.getDatabase(
            requireContext().applicationContext
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(
                Lifecycle.State.STARTED
            ) {
                db.waterDao().getAllWaterEntries()
                    .collectLatest { entries ->
                        waterList.clear()
                        waterList.addAll(entries)
                        waterAdapter.notifyDataSetChanged()
                    }
            }
        }
    }
}
