package com.holiday

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HolidayAdapter(private val holidays: List<Holiday>) : RecyclerView.Adapter<HolidayAdapter.ViewHolder>() {

inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
    val nameTextView: TextView = itemView.findViewById(R.id.nameTextView)
    val typeTextView: TextView = itemView.findViewById(R.id.typeTextView)

    init {
        itemView.setOnClickListener(this)
    }

    override fun onClick(view: View) {
        val holiday = holidays[adapterPosition]
        val intent = Intent(view.context, HolidayDetailsActivity::class.java)
        intent.putExtra("holiday", holiday)
        view.context.startActivity(intent)
    }
}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_holiday, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val holiday = holidays[position]
        holder.dateTextView.text = holiday.date
        holder.nameTextView.text = holiday.name
        holder.typeTextView.text = holiday.type
    }

    override fun getItemCount(): Int {
        return holidays.size
    }
}