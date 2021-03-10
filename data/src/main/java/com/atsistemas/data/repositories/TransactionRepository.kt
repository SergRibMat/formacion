package com.atsistemas.data.repositories

import androidx.lifecycle.LiveData
import com.atsistemas.data.commons.BaseRepository
import com.atsistemas.data.local.BankDatabase
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.remote.ITransactionAPI

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class TransactionRepository(private val api: ITransactionAPI, private val bankDB: BankDatabase): BaseRepository() {

    val mTransactions: LiveData<List<TransactionDTO>> by lazy {
        bankDB.transactionDao().load()
    }

    //API
    suspend fun getTransactions(): List<TransactionDTO>{
        return api.getTransactions()
    }

    suspend fun getTransactionsAndSave(): String{
        val trans = api.getTransactions()
        saveTransactions(trans)
        return "Ok"
    }

    //Database
    suspend fun saveTransactions(transactions: List<TransactionDTO>){
        bankDB.transactionDao().save(transactions)
    }

    suspend fun loadTransactions(): List<TransactionDTO>{
        return bankDB.transactionDao().load()
    }
}