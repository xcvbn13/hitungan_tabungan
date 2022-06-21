package org.d3if4094.hitungantabungan.model

import com.squareup.moshi.Json

data class TentangAplikasi(
    @Json(name = "tentang_aplikasi")
    val tentang : String
)
