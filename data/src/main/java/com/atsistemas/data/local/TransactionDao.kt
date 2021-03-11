package com.atsistemas.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.atsistemas.data.commons.Constants.TABLE_TRANSACTIONS
import com.atsistemas.data.models.TransactionDTO

/**
 * Created by Juan Manuel Rincón on 3/10/21.
 */
@Dao
interface TransactionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun save(transactions: List<TransactionDTO>)

    @Query("SELECT * FROM `$TABLE_TRANSACTIONS`")
    fun load(): LiveData<List<TransactionDTO>>

    @Query("DELETE FROM `$TABLE_TRANSACTIONS`")
    fun deleteAll()
}