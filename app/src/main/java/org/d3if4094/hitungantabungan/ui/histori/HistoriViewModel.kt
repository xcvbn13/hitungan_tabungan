package org.d3if4094.hitungantabungan.ui.histori

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4094.hitungantabungan.db.HistoriDao
import org.d3if4094.hitungantabungan.db.HistoriEntity

class HistoriViewModel(private val db:HistoriDao): ViewModel() {
    val data = db.getHistori()

    fun deleteHistory(historiEntity: HistoriEntity) = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.delete(historiEntity)
        }
    }
    fun deleteAllHistory() = viewModelScope.launch {
        withContext(Dispatchers.IO){
            db.deleteAllHistory()
        }
    }


}