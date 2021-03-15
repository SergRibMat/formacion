package com.atsistemas.myapplication.home_activity.home.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.data.models.TransactionDTO
import com.atsistemas.data.remote.ResultHandler
import com.atsistemas.data.repositories.TransactionRepository
import com.atsistemas.myapplication.commons.BaseViewModel
import com.atsistemas.myapplication.commons.Constants.NETWORK_ERROR
import com.atsistemas.myapplication.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Juan Manuel Rincón on 3/9/21.
 */
class HomeViewModel(private val repository: TransactionRepository): BaseViewModel() {


    val transactionsList: LiveData<List<TransactionDTO>> = repository.mTransactions


    fun fetchTransactions(){
        _isLoading.value = true
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getTransactionsAndSave()){
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
            _isLoading.postValue(false)
        }
    }


    fun clearList(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTransactions()
        }
    }



}