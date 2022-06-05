package com.pd.mydictionary.di

import android.app.Application
import com.pd.mydictionary.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/** Тут мы прописываем все наши модули, включая AndroidSupportInjectionModule.
Этот класс создаётся Dagger’ом. Он как раз связан с аннотацией ContributesAndroidInjector выше
и позволяет внедрять в Activity все необходимые зависимости */

@Component(
    modules = [
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class,
        ActivityModule::class,
        AndroidSupportInjectionModule::class
    ]
)

@Singleton
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(englishVocabularyApp: App)

}