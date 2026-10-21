
package com.example.bitfitFragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class DashboardFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(
            R.layout.fragment_dashboard,
            container,
            false
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val totalWaterTv =
            view.findViewById<TextView>(R.id.totalWaterTv)

        val entryCountTv =
            view.findViewById<TextView>(R.id.entryCountTv)

        val db = AppDatabase.getDatabase(
            requireContext().applicationContext
        )

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                db.waterDao().getAllWaterEntries().collectLatest { entries ->

                    val total = entries.sumOf { it.waterAmount }

                    totalWaterTv.text = "$total oz"
                    entryCountTv.text = entries.size.toString()
                }
            }
        }
    }
}
