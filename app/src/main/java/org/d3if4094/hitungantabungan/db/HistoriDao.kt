package org.d3if4094.hitungantabungan.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface HistoriDao  {
    @Insert
    fun insert(historiEntity: HistoriEntity)

    @Query("SELECT * FROM histori ORDER BY id DESC")
    fun getHistori(): LiveData<List<HistoriEntity>>

    @Delete
    fun delete(vararg historiEntity: HistoriEntity)

    @Update
    fun updateUsers(vararg historiEntity: HistoriEntity)
}