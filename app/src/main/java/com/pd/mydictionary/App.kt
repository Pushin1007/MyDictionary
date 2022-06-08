package com.pd.mydictionary

import android.app.Application
import com.pd.mydictionary.di.DaggerAppComponent
import com.pd.mydictionary.di.application
import com.pd.mydictionary.di.mainScreen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import org.koin.core.context.startKoin
import javax.inject.Inject

//class App : Application(), HasAndroidInjector {
//
//    @Inject
//    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Any>
//
//    override fun androidInjector(): AndroidInjector<Any> {
//        return dispatchingAndroidInjector
//    }
//
//    override fun onCreate() {
//        super.onCreate()
//        DaggerAppComponent.builder()
//            .application(this)
//            .build()
//            .inject(this)
//    }
//}

/** Инициализация Koin в приложении
 * @param startKoin
 * Важная деталь - Нужно убедиться, что TranslatorApp прописан в Manifests */

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }
}