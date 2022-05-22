package org.d3if4094.hitungantabungan.ui.hitung

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4094.hitungantabungan.db.HistoriDao
import org.d3if4094.hitungantabungan.db.HistoriEntity
import org.d3if4094.hitungantabungan.model.HasilHitung
import org.d3if4094.hitungantabungan.model.hitungTabungan

class HitungViewModel(private val db:HistoriDao) : ViewModel(){
    private val hasilHitung = MutableLiveData<HasilHitung?>()

    fun hitungTabungan(targetUang:Double,jumlahUang:Double) {
        val dataHistori = HistoriEntity(
            depo = jumlahUang,
            target = targetUang
        )
        hasilHitung.value = dataHistori.hitungTabungan()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataHistori)
            }
        }
    }

    fun getHasilHitung() : LiveData<HasilHitung?> = hasilHitung
}