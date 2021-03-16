package com.atsistemas.data.commons

import androidx.datastore.preferences.core.stringPreferencesKey

/**
 * Created by Juan Manuel Rincón on 3/10/21.
 */
object Constants {
    const val DATABASE_NAME = "bank_db"
    const val TABLE_TRANSACTIONS = "transaction"
    const val TABLE_PROFILEDATA = "profiledata"
    val DATA_STORE_NAME = stringPreferencesKey("NAME")
    val DATA_STORE_SURNAME = stringPreferencesKey("SURNAME")
}