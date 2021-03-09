package com.atsistemas.data.repositories

import com.atsistemas.data.commons.BaseRepository
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.remote.ITransactionAPI

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class TransactionRepository(private val api: ITransactionAPI): BaseRepository() {

    suspend fun getTransactions(): List<TransactionDTO>{
        return api.getTransactions()
    }
}