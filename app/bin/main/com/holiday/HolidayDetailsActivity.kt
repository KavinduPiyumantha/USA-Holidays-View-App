package com.holiday

//import android.R
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.holiday.R


class HolidayDetailsActivity : AppCompatActivity() {

    private lateinit var dateTextView: TextView
    private lateinit var nameTextView: TextView
    private lateinit var typeTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_holiday_details)

        dateTextView = findViewById(R.id.dateTextView)
        nameTextView = findViewById(R.id.nameTextView)
        typeTextView = findViewById(R.id.typeTextView)

        val intent = intent
        if (intent != null && intent.hasExtra("holiday")) {
            val holiday = intent.getParcelableExtra<Holiday>("holiday")
            if (holiday != null) {
                dateTextView.text = holiday.date
                nameTextView.text = holiday.name
                typeTextView.text = holiday.type
            }
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""
    }


}