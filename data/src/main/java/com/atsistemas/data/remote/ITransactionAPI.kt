package com.atsistemas.data.remote

import com.atsistemas.data.models.TransactionDTO
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
interface ITransactionAPI {
    @GET("/transactions.json")
    suspend fun getTransactions(): Response<List<TransactionDTO>>
}