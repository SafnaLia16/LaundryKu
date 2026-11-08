package com.laundryku.app

import java.text.NumberFormat
import java.util.Locale

data class Transaksi(
    val layanan: String,
    val berat: Int,
    val tanggal: String,
    val total: Int,
    val status: String = "Selesai"
)

/** Data sederhana di memori (demo). Ganti dengan Room / API bila diperlukan. */
object AppData {
    var namaLengkap = "Safna Lia"
    var username = "safna123"
    var saldo = 50000

    val layanan = linkedMapOf(
        "Cuci Kering" to 15000,
        "Cuci + Setrika" to 15000,
        "Setrika Saja" to 10000
    )

    val riwayat = mutableListOf(
        Transaksi("Cuci + Setrika", 2, "20 Okt 2025", 30000),
        Transaksi("Cuci Kering", 1, "18 Okt 2025", 15000),
        Transaksi("Cuci + Setrika", 3, "15 Okt 2025", 45000)
    )

    fun rupiah(n: Int): String =
        "Rp " + NumberFormat.getInstance(Locale("in", "ID")).format(n)
}
