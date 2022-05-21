package org.d3if4094.hitungantabungan.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [HistoriEntity::class], version = 1, exportSchema = false)
abstract class HistoriDb:RoomDatabase() {

    abstract val dao:HistoriDao

    companion object{
        @Volatile
        private var INSTANCE:HistoriDb? = null

        fun getInstance(context: Context):HistoriDb{
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        HistoriDb::class.java,
                        "histori.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}