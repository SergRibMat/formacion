package com.atsistemas.data.local

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.lifecycle.LiveData
import com.atsistemas.data.commons.Constants
import com.atsistemas.data.commons.dataStore
import com.atsistemas.data.models.ProfileData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProfileDataStore(private val context: Context){

    var profileDataName: Flow<String> = context.dataStore.data.map {
        it[Constants.DATA_STORE_NAME] ?: ""
    }

    var profileDataSurname: Flow<String> = context.dataStore.data.map {
        it[Constants.DATA_STORE_SURNAME] ?: ""
    }

    suspend fun storeProfileData(profileData: ProfileData) {
        context.dataStore.edit {
            it[Constants.DATA_STORE_NAME] = profileData.name
            it[Constants.DATA_STORE_SURNAME] = profileData.surname
        }
    }

}
