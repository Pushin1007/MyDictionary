package com.pd.mydictionary

import android.app.Application

import com.pd.mydictionary.di.application
import com.pd.mydictionary.di.mainScreen
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //меняем модили даггера на коин
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}