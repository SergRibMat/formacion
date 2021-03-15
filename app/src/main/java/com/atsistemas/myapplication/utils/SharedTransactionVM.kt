package com.atsistemas.myapplication.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.atsistemas.data.models.TransactionDTO

/**
 * Created by Juan Manuel Rincón on 3/15/21.
 */
class SharedTransactionVM: ViewModel() {

    private var _transaction = MutableLiveData<TransactionDTO>()

    val transaction: LiveData<TransactionDTO>
        get() = _transaction

    fun setTransaction(transaction: TransactionDTO){
        _transaction.value = transaction
    }
}