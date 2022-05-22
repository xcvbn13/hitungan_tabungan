package org.d3if4094.hitungantabungan.ui.histori

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import org.d3if4094.hitungantabungan.R
import org.d3if4094.hitungantabungan.databinding.ItemHistoryBinding
import org.d3if4094.hitungantabungan.db.HistoriEntity
import org.d3if4094.hitungantabungan.model.hitungTabungan
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter: ListAdapter<HistoriEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object{
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<HistoriEntity>() {
            override fun areItemsTheSame(
                oldData: HistoriEntity, newData: HistoriEntity
            ): Boolean {
                return oldData.id == newData.id
            }
            override fun areContentsTheSame(
                oldData: HistoriEntity, newData: HistoriEntity
            ): Boolean {
                return oldData == newData
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            val binding = ItemHistoryBinding.inflate(inflater, parent, false)
            return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoryBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        private val dateFormatter = SimpleDateFormat(
            "dd MMMM yyyy",
            Locale("id", "ID")
        )

        fun bind(item: HistoriEntity) = with(binding) {
            val hasilHitung = item.hitungTabungan()

            tanggalTextView.text = dateFormatter.format(Date(item.tanggal))
            depoTextView.text = root.context.getString(R.string.depo_x, item.depo.toString())
            targetTextView.text = root.context.getString(R.string.target_x,item.target.toString())
            prediksiTextView.text = root.context.getString(R.string.hari_x,hasilHitung.hasil)

        }

    }

}