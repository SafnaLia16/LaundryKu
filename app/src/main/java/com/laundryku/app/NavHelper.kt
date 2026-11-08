package com.laundryku.app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView

/** Mengatur Bottom Navigation (Beranda - Riwayat - Akun). */
fun AppCompatActivity.setupBottomNav(selected: Int) {
    val nav = findViewById<BottomNavigationView>(R.id.bottomNav)
    nav.selectedItemId = selected
    nav.setOnItemSelectedListener { item ->
        if (item.itemId == selected) return@setOnItemSelectedListener true
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(
                    Intent(this, MainActivity::class.java)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
                )
                finish()
                true
            }
            R.id.nav_riwayat -> {
                startActivity(Intent(this, HistoryActivity::class.java))
                true
            }
            R.id.nav_akun -> {
                startActivity(Intent(this, SettingsActivity::class.java))
                false // tetap di tab sekarang
            }
            else -> false
        }
    }
}
