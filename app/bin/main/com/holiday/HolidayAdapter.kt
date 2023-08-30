package com.holiday

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HolidayAdapter(private val holidays: List<Holiday>) : RecyclerView.Adapter<HolidayAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
        val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holiday, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holiday = holidays[position]
        holder.dateTextView.text = holiday.getDate()
        holder.nameTextView.text = holiday.getName()
        holder.typeTextView.text = holiday.getType()
    }
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        val holiday = holidays[position]
//        val formattedDate = "Date: ${holiday.getDate()}"
//        holder.dateTextView.text = formattedDate
//        holder.nameTextView.text = holiday.getName()
//        holder.typeTextView.text = holiday.getType()
//    }

    override fun getItemCount(): Int {
        return holidays.size
    }
}