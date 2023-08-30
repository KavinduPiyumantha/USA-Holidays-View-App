package com.holiday

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException


class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: HolidayAdapter
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh)

        recyclerView.layoutManager = LinearLayoutManager(this)

        // Check network status and fetch data if network is available

        val connectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork
        val capabilities = connectivityManager.getNetworkCapabilities(network)
        if (capabilities != null && (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) || capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR))) {
            fetchHolidayData()
            swipeRefreshLayout.isEnabled = true
        } else {
            val message = "No internet connection. Please check your network settings."
            Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show()
            swipeRefreshLayout.isEnabled = false
        }

        // Set the refresh listener for the SwipeRefreshLayout
        swipeRefreshLayout.setOnRefreshListener {
            fetchHolidayData()
        }
    }

    // Fetch holiday data from API
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
                    swipeRefreshLayout.isRefreshing = false
                }
            } catch (e: IOException) {
                withContext(Dispatchers.Main) {
                    val message = "No internet connection. Please check your network settings."
                    Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show()
                    swipeRefreshLayout.isRefreshing = false
                }
            }
        }
    }
}