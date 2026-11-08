package com.laundryku.app

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class OrderActivity : AppCompatActivity() {
    private var berat = 1
    private lateinit var spLayanan: Spinner
    private lateinit var tvBerat: TextView
    private lateinit var tvTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order)

        spLayanan = findViewById(R.id.spLayanan)
        tvBerat = findViewById(R.id.tvBerat)
        tvTotal = findViewById(R.id.tvTotal)

        findViewById<ImageView>(R.id.btnBack).setOnClickListener { finish() }

        val names = AppData.layanan.keys.toList()
        spLayanan.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, names)
        spLayanan.setSelection(names.indexOf("Cuci + Setrika"))
        spLayanan.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p: AdapterView<*>?, v: View?, pos: Int, id: Long) = updateTotal()
            override fun onNothingSelected(p: AdapterView<*>?) {}
        }

        findViewById<View>(R.id.btnMinus).setOnClickListener {
            if (berat > 1) { berat--; updateTotal() }
        }
        findViewById<View>(R.id.btnPlus).setOnClickListener {
            berat++; updateTotal()
        }

        findViewById<Button>(R.id.btnPesan).setOnClickListener {
            val layanan = spLayanan.selectedItem as String
            val total = hitungTotal()
            if (AppData.saldo < total) {
                Toast.makeText(this, "Saldo tidak cukup, silakan Top Up", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }
            val tanggal = SimpleDateFormat("dd MMM yyyy", Locale("in", "ID")).format(Date())
            AppData.saldo -= total
            AppData.riwayat.add(0, Transaksi(layanan, berat, tanggal, total, "Diproses"))
            Toast.makeText(this, "Pesanan berhasil dibuat!", Toast.LENGTH_SHORT).show()
            finish()
        }
        updateTotal()
    }

    private fun hitungTotal(): Int {
        val harga = AppData.layanan[spLayanan.selectedItem as String] ?: 0
        return harga * berat
    }

    private fun updateTotal() {
        tvBerat.text = berat.toString()
        tvTotal.text = AppData.rupiah(hitungTotal())
    }
}
