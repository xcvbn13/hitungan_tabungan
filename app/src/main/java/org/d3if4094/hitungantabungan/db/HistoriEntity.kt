package org.d3if4094.hitungantabungan.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "histori")
data class HistoriEntity(
    @PrimaryKey(autoGenerate = true)
    var id:Long = 0L,
    var tanggal: Long = System.currentTimeMillis(),
    var depo:Double,
    var target:Double
)
