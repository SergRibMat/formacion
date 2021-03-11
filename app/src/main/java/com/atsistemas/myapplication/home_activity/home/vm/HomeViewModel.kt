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

    private var _showMessage = SingleLiveEvent<String>()
    val showMessage: LiveData<String>
        get() = _showMessage

    private var _showError = SingleLiveEvent<String>()
    val showError: LiveData<String>
        get() = _showError

    fun fetchTransactions(){
        viewModelScope.launch (Dispatchers.IO) {
            when (val result = repository.getTransactionsAndSave()){
                is ResultHandler.Success -> {
                    showMessage(result.data)
                }
                else -> {
                    setShowError(result)
                }
            }
        }
    }

    fun saveTransactions(transactions: List<TransactionDTO>){
        viewModelScope.launch (Dispatchers.IO){
            repository.saveTransactions(transactions)
        }
    }


    fun clearList(){
        viewModelScope.launch (Dispatchers.IO){
            repository.deleteTransactions()
        }
    }

    fun showMessage(text: String){
        _showMessage.postValue(text)
    }

    fun setShowError(resultHandler: ResultHandler<Any>){
        when (resultHandler){
            is ResultHandler.NetworkError -> {
                _showError.postValue(NETWORK_ERROR)
            }
            is ResultHandler.HttpError -> {
                _showError.postValue("${resultHandler.code!!}")
            }
            is ResultHandler.GenericError -> {
                _showError.postValue(resultHandler.message!!)
            }
            else -> {
                _showError.postValue(NETWORK_ERROR)
            }
        }
    }

}