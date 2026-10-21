package com.example.bitfitFragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class WaterAdapter(
    private val waterList: List<WaterEntity>
) : RecyclerView.Adapter<WaterAdapter.WaterViewHolder>() {

    class WaterViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        val amountTv: TextView =
            itemView.findViewById(R.id.amountTv)

        val dateTv: TextView =
            itemView.findViewById(R.id.dateTv)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WaterViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.water_item, parent, false)

        return WaterViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: WaterViewHolder,
        position: Int
    ) {
        val entry = waterList[position]
        holder.amountTv.text = "${entry.waterAmount} oz"
        holder.dateTv.text = entry.date
    }

    override fun getItemCount(): Int = waterList.size
}