package org.d3if4094.hitungantabungan.ui.hitung

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.work.ExistingWorkPolicy
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.d3if4094.hitungantabungan.MainActivity
import org.d3if4094.hitungantabungan.db.HistoriDao
import org.d3if4094.hitungantabungan.db.HistoriEntity
import org.d3if4094.hitungantabungan.model.HasilHitung
import org.d3if4094.hitungantabungan.model.hitungTabungan
import org.d3if4094.hitungantabungan.network.Worker
import java.util.concurrent.TimeUnit

class HitungViewModel(private val db:HistoriDao) : ViewModel(){
    private val hasilHitung = MutableLiveData<HasilHitung?>()
    private var waktu : Long = 0

    fun hitungTabungan(targetUang:Double,jumlahUang:Double) {
        val dataHistori = HistoriEntity(
            depo = jumlahUang,
            target = targetUang
        )
        hasilHitung.value = dataHistori.hitungTabungan()
        waktu = dataHistori.hitungTabungan().hasil

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                db.insert(dataHistori)
            }
        }
    }

    fun getHasilHitung() : LiveData<HasilHitung?> = hasilHitung

    fun scheduleUpdater(app: Application) {

        val request = OneTimeWorkRequestBuilder<Worker>()
            .setInitialDelay(waktu, TimeUnit.DAYS)
            .build()
        WorkManager.getInstance(app).enqueueUniqueWork(
            MainActivity.CHANNEL_ID,
            ExistingWorkPolicy.REPLACE,
            request
        )
    }


}

