package com.atsistemas.myapplication.di

import com.atsistemas.myapplication.home_activity.vm.HomeViewModelActivity
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */

val uiModule = module {
    viewModel { HomeViewModelActivity() }
}