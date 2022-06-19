package org.d3if4094.hitungantabungan.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

data class TentangAplikasi(
    @Json(name = "tentang_aplikasi")
    val tentang : String
)
