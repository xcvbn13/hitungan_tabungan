package org.d3if4094.hitungantabungan.ui.histori

import androidx.lifecycle.ViewModel
import org.d3if4094.hitungantabungan.db.HistoriDao

class HistoriViewModel(db:HistoriDao): ViewModel() {
    val data = db.getHistori()
}