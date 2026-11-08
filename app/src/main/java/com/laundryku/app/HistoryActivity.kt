package com.laundryku.app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        val rv = findViewById<RecyclerView>(R.id.rvRiwayat)
        rv.layoutManager = LinearLayoutManager(this)
        adapter = HistoryAdapter(AppData.riwayat)
        rv.adapter = adapter
        setupBottomNav(R.id.nav_riwayat)
    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
    }
}
