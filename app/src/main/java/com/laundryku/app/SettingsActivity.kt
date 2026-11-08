package com.laundryku.app

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.switchmaterial.SwitchMaterial

class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        findViewById<TextView>(R.id.tvNama).text = AppData.namaLengkap
        findViewById<TextView>(R.id.tvUsername).text = "Username: ${AppData.username}"
        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        fun soon(id: Int, msg: String) =
            findViewById<View>(id).setOnClickListener {
                Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
            }
        soon(R.id.rowProfil, "Ubah Profil (segera hadir)")
        soon(R.id.rowPassword, "Ubah Password (segera hadir)")
        soon(R.id.rowTema, "Tema Aplikasi (segera hadir)")

        findViewById<View>(R.id.rowTentang).setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("LaundryKu")
                .setMessage("Versi 1.0\nBersih, Rapi, dan Praktis")
                .setPositiveButton("OK", null)
                .show()
        }
        findViewById<SwitchMaterial>(R.id.swNotif).setOnCheckedChangeListener { _, on ->
            Toast.makeText(this, if (on) "Notifikasi aktif" else "Notifikasi nonaktif", Toast.LENGTH_SHORT).show()
        }

        val logout = {
            startActivity(
                Intent(this, LoginActivity::class.java)
                    .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            )
        }
        findViewById<View>(R.id.rowKeluar).setOnClickListener { logout() }
        findViewById<Button>(R.id.btnLogout).setOnClickListener { logout() }
    }
}
