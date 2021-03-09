package com.atsistemas.myapplication.home_activity.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.repositories.TransactionRepository
import com.atsistemas.myapplication.commons.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class HomeViewModel(private val repository: TransactionRepository): BaseViewModel() {

    private var _transacionList = MutableLiveData<MutableList<TransactionDTO>>()
    val transactionList: LiveData<MutableList<TransactionDTO>>
        get() = _transacionList


    fun fetchTransactions(){
        viewModelScope.launch (Dispatchers.IO) {
            _transacionList.postValue(repository.getTransactions() as MutableList<TransactionDTO>)
        }
    }

}