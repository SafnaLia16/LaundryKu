package com.laundryku.app

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class HistoryAdapter(private val items: List<Transaksi>) :
    RecyclerView.Adapter<HistoryAdapter.VH>() {

    class VH(v: View) : RecyclerView.ViewHolder(v) {
        val layanan: TextView = v.findViewById(R.id.tvLayanan)
        val detail: TextView = v.findViewById(R.id.tvDetail)
        val harga: TextView = v.findViewById(R.id.tvHarga)
        val status: TextView = v.findViewById(R.id.tvStatus)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(LayoutInflater.from(parent.context).inflate(R.layout.item_riwayat, parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(h: VH, position: Int) {
        val t = items[position]
        h.layanan.text = t.layanan
        h.detail.text = "${t.berat} kg • ${t.tanggal}"
        h.harga.text = AppData.rupiah(t.total)
        h.status.text = t.status
        val selesai = t.status == "Selesai"
        h.status.setTextColor(Color.parseColor(if (selesai) "#2E9E5B" else "#E67E22"))
        h.status.background.mutate()
            .setTint(Color.parseColor(if (selesai) "#DDF5E4" else "#FDEBD3"))
    }
}
