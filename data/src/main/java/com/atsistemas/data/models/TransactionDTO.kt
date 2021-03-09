package com.atsistemas.data.models

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
data class TransactionDTO(
        val id: String,
        val date: String,
        val amount: String,
        val description: String?,
        val fee: String?,
        val total: String
)
