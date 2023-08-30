package com.holiday

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.util.Arrays


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HolidayAdapter
    private lateinit var holidays: List<Holiday>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fetchHolidayData();

    }

    private fun fetchHolidayData() {
        val url = "https://date.nager.at/api/v2/publicholidays/2023/US"
        val client = OkHttpClient()
        val request = Request.Builder().url(url).build()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val response = client.newCall(request).execute()
                val body = response.body
                val json = body?.string()
                val holidays = Gson().fromJson(json, Array<Holiday>::class.java).toList()

                withContext(Dispatchers.Main) {
                    adapter = HolidayAdapter(holidays)
                    recyclerView.adapter = adapter
                }
            } catch (e: IOException) {
                // Handle network errors here
            }
        }
    }
}