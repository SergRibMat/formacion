package com.atsistemas.data.repositories

import androidx.lifecycle.LiveData
import com.atsistemas.data.commons.BaseRepository
import com.atsistemas.data.local.BankDatabase
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.remote.ITransactionAPI
import com.atsistemas.data.remote.ResultHandler
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
//    suspend fun getTransactions(): List<TransactionDTO>{
//        return api.getTransactions()
//    }

    //API
    suspend fun getTransactionsAndSave(): ResultHandler<String> {
        //Call to API and save in Room
        when (val result = safeApiCall(call = { api.getTransactions() })) {
            is ResultHandler.Success -> {
                //Sort the list
                result.data.let {
                    //Save data in Room
                    bankDB.transactionDao().save(result.data)
                }
                //It is not necessary to return nothing, magic is done with liveData in Room
                return ResultHandler.Success("Successful update")
            }
            is ResultHandler.GenericError -> return result
            is ResultHandler.HttpError -> return result
            is ResultHandler.NetworkError -> return result

        }

    }

    //Function to return data or error
    suspend inline fun <T : Any> safeApiCall(call: () -> Response<T>): ResultHandler<T> {

        return try{
            val response = call.invoke()
            if (response.isSuccessful)
                ResultHandler.Success(response.body()!!)
            else {
                ResultHandler.HttpError(response.code(), response.message())
            }
        }catch (throwable: Throwable){
            when (throwable) {
                is IOException -> ResultHandler.NetworkError
                is HttpException -> {
                    ResultHandler.HttpError(throwable.code(), throwable.message())
                }
                else -> {
                    ResultHandler.GenericError(throwable.message)
                }
            }
        }
    }

    //Database
    suspend fun saveTransactions(transactions: List<TransactionDTO>){
        bankDB.transactionDao().save(transactions)
    }

//    suspend fun loadTransactions(): List<TransactionDTO>{
//        return bankDB.transactionDao().load()
//    }

    suspend fun deleteTransactions(){
        bankDB.transactionDao().deleteAll()
    }
}