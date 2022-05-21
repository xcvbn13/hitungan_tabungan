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

class HitungViewModel(private val db:HistoriDao) : ViewModel(){
    private val hasilBmi = MutableLiveData<HasilHitung?>()

    fun hitungTabungan(targetUang:Double,jumlahUang:Double) {
        val hasil = targetUang / jumlahUang
        val hasilAkhir = hasil.toInt().toString()
        hasilBmi.value = HasilHitung(hasilAkhir)

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                val dataHistori = HistoriEntity(
                    depo = jumlahUang,
                    target = targetUang
                )
                db.insert(dataHistori)
            }
        }
    }

    fun getHasilHitung() : LiveData<HasilHitung?> = hasilBmi
}