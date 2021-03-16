package com.atsistemas.myapplication.home_activity.profile.vm

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.atsistemas.data.repositories.TransactionRepository
import com.atsistemas.myapplication.commons.BaseViewModel
import com.atsistemas.data.models.ProfileData
import com.atsistemas.myapplication.utils.SingleLiveEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: TransactionRepository): BaseViewModel() {


    val profileData: LiveData<ProfileData> = repository.profileData


    fun saveProfileData(name: String, surname: String){
        viewModelScope.launch (Dispatchers.IO) {
            repository.saveProfileData(ProfileData("0",name, surname))
        }



    }


}