package com.atsistemas.data.repositories

import androidx.lifecycle.LiveData
import com.atsistemas.data.commons.BaseRepository
import com.atsistemas.data.local.BankDatabase
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.remote.ITransactionAPI
import com.atsistemas.data.remote.ResultHandler
import com.atsistemas.data.utils.TransactionsUtil
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class TransactionRepository(private val api: ITransactionAPI, private val bankDB: BankDatabase): BaseRepository() {

    val mTransactions: LiveData<List<TransactionDTO>> by lazy {
        bankDB.transactionDao().load()
    }

    //API
    suspend fun getTransactionsAndSave(): ResultHandler<String> {
        //Call to API and save in Room
        when (val result = safeApiCall(call = { api.getTransactions() })) {
            is ResultHandler.Success -> {
                //Sort the list
                result.data.let {

                    var sortedList = result.data.toMutableList()
                        .sortedWith(compareByDescending { it.date })
                    sortedList = TransactionsUtil.filterTransactions(sortedList)
                    //Save data in Room
                    bankDB.transactionDao().save(sortedList)
                }
                //It is not necessary to return nothing, magic is done with liveData in Room
                return ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> return result
            is ResultHandler.HttpError -> return result
            is ResultHandler.NetworkError -> return result

        }

    }

    suspend fun deleteTransactions(){
        bankDB.transactionDao().deleteAll()
    }
}