package com.pd.mydictionary

import android.app.Application

import com.pd.mydictionary.di.application
import com.pd.mydictionary.di.historyScreen
import com.pd.mydictionary.di.mainScreen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class App : Application() {

    override fun onCreate() {
        super.onCreate()
        //меняем модили даггера на коин
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }
}