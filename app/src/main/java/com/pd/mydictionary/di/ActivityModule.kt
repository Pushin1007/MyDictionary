package com.pd.mydictionary.di

import dagger.android.ContributesAndroidInjector

abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeMainActivity(): MainActivity
}