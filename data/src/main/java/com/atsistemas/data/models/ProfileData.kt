package com.atsistemas.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.atsistemas.data.commons.Constants

@Entity(tableName = Constants.TABLE_PROFILEDATA)
data class ProfileData(
    @PrimaryKey val id: String,
    var name: String,
    var surname: String
)