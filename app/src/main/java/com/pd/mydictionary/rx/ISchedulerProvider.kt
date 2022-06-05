package com.pd.mydictionary.rx

import io.reactivex.rxjava3.core.Scheduler


// вспомогательный код для тестирования rx
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}
