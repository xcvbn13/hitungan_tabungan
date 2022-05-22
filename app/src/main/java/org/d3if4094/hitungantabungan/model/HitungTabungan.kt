package org.d3if4094.hitungantabungan.model

import org.d3if4094.hitungantabungan.db.HistoriEntity

fun HistoriEntity.hitungTabungan():HasilHitung{
    val hasil = target / depo
    val hasilAkhir = hasil.toInt().toString()

    return HasilHitung(hasilAkhir)
}