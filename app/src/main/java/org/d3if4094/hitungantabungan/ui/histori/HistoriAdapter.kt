package org.d3if4094.hitungantabungan.ui.histori

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import org.d3if4094.hitungantabungan.R
import org.d3if4094.hitungantabungan.databinding.ItemHistoryBinding
import org.d3if4094.hitungantabungan.db.HistoriEntity
import org.d3if4094.hitungantabungan.model.hitungTabungan
import java.text.SimpleDateFormat
import java.util.*

class HistoriAdapter(private val viewModel: HistoriViewModel,private val context: Context ): ListAdapter<HistoriEntity, HistoriAdapter.ViewHolder>(DIFF_CALLBACK) {


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
            return ViewHolder(binding,viewModel,context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(
        private val binding: ItemHistoryBinding,
        private val viewModel: HistoriViewModel,
        private val context: Context
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

            buttonDelete.setOnClickListener {
                MaterialAlertDialogBuilder(context)
                    .setMessage(R.string.konfirmasi_hapus_item)
                    .setPositiveButton(R.string.hapus) { _, _ ->
                        viewModel.deleteHistory(item)
                    }
                    .setNegativeButton(R.string.batal) { dialog, _ ->
                        dialog.cancel()
                    }
                    .show()
            }
        }

    }

}