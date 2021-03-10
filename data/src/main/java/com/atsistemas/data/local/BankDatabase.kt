package com.atsistemas.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.atsistemas.data.commons.Constants.DATABASE_NAME
import com.atsistemas.data.models.TransactionDTO

/**
 * Created by Juan Manuel Rincón on 3/10/21.
 */
@Database(entities = [TransactionDTO::class], version = 1)
abstract class BankDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao

    companion object{
        @Volatile
        private var INSTANCE: BankDatabase? = null

        fun getInstance(context: Context): BankDatabase =
                INSTANCE ?: synchronized(this){
                    INSTANCE ?: buildDatabase(context.applicationContext).also {
                        INSTANCE = it
                    }
                }

        private fun buildDatabase(appContext: Context): BankDatabase{
            return Room.databaseBuilder(appContext, BankDatabase::class.java, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .build()
        }
    }
}