package com.atsistemas.myapplication.di

import com.atsistemas.myapplication.home_activity.HomeViewModelActivity
import com.atsistemas.myapplication.home_activity.home.vm.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */

val uiModule = module {
    viewModel { HomeViewModelActivity() }
    viewModel { HomeViewModel(get()) }
}