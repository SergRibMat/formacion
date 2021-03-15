package com.atsistemas.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atsistemas.data.commons.Constants

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
@Entity(tableName = Constants.TABLE_TRANSACTIONS)
data class TransactionDTO(
    @PrimaryKey val id: String,
    var date: String,
    val amount: String,
    val description: String?,
    val fee: String?,
    var total: String?
)
