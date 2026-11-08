package com.laundryku.app

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val etNama = findViewById<TextInputEditText>(R.id.etNama)
        val etUser = findViewById<TextInputEditText>(R.id.etUsername)
        val etPass = findViewById<TextInputEditText>(R.id.etPassword)
        val etKonf = findViewById<TextInputEditText>(R.id.etKonfirmasi)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }
        findViewById<TextView>(R.id.tvLogin).setOnClickListener { finish() }

        findViewById<Button>(R.id.btnDaftar).setOnClickListener {
            val nama = etNama.text.toString().trim()
            val user = etUser.text.toString().trim()
            val pass = etPass.text.toString()
            val konf = etKonf.text.toString()
            when {
                nama.isEmpty() || user.isEmpty() || pass.isEmpty() ->
                    toast("Semua kolom wajib diisi")
                pass != konf -> toast("Konfirmasi password tidak sama")
                else -> {
                    AppData.namaLengkap = nama
                    AppData.username = user
                    toast("Pendaftaran berhasil, silakan login")
                    finish()
                }
            }
        }
    }

    private fun toast(msg: String) = Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
}
