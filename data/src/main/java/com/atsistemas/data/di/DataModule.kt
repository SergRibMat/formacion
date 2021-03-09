package com.atsistemas.data.di

import com.atsistemas.data.di.providers.provideMockInterceptor
import com.atsistemas.data.di.providers.provideOkHttpClient
import org.koin.dsl.module

/**
 * Created by Juan Manuel Rincón on 3/8/21.
 */

val dataModule = module {
    single { provideOkHttpClient(get()) }
    single { provideMockInterceptor(get()) }
    //rellenar
}