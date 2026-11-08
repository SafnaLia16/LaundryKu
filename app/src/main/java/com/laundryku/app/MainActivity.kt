package com.laundryku.app

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var tvSaldo: TextView
    private lateinit var tvHalo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSaldo = findViewById(R.id.tvSaldo)
        tvHalo = findViewById(R.id.tvHalo)

        findViewById<android.view.View>(R.id.cardPesan).setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
        }
        findViewById<android.view.View>(R.id.cardRiwayat).setOnClickListener {
            startActivity(Intent(this, HistoryActivity::class.java))
        }
        findViewById<android.view.View>(R.id.cardSetting).setOnClickListener {
            startActivity(Intent(this, SettingsActivity::class.java))
        }
        val topUp = {
            AlertDialog.Builder(this)
                .setTitle("Top Up Saldo")
                .setMessage("Tambah saldo Rp 50.000?")
                .setPositiveButton("Ya") { _, _ ->
                    AppData.saldo += 50000
                    refresh()
                }
                .setNegativeButton("Batal", null)
                .show()
            Unit
        }
        findViewById<android.view.View>(R.id.cardTopup).setOnClickListener { topUp() }
        findViewById<android.view.View>(R.id.cardSaldo).setOnClickListener { topUp() }
        findViewById<TextView>(R.id.tvLihatSemua).setOnClickListener {
            Toast.makeText(this, "Belum ada info lainnya", Toast.LENGTH_SHORT).show()
        }

        setupBottomNav(R.id.nav_home)
    }

    override fun onResume() {
        super.onResume()
        refresh()
    }

    private fun refresh() {
        val namaDepan = AppData.namaLengkap.trim().substringBefore(" ")
        tvHalo.text = "Halo, $namaDepan! 👋"
        tvSaldo.text = AppData.rupiah(AppData.saldo)
    }
}
