package com.atsistemas.myapplication.commons

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.atsistemas.myapplication.commons.uicomponents.ErrorDialog

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */
abstract class BaseFragment: Fragment() {

    var errorDialog : ErrorDialog? = null

    abstract fun loadObservers()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadObservers()
    }


}