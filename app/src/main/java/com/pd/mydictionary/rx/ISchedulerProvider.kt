package com.pd.mydictionary.rx

import io.reactivex.Scheduler

// вспомогательный код для тестирования rx
interface ISchedulerProvider {

    fun ui(): Scheduler

    fun io(): Scheduler
}
