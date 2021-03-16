package com.atsistemas.myapplication.commons

import android.content.Context
import androidx.appcompat.app.AppCompatActivity

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */
abstract class BaseActivity: AppCompatActivity() {

    companion object {
        private var instance: BaseActivity? = null

        fun applicationContext() : Context {
            return instance!!.applicationContext
        }
    }


}