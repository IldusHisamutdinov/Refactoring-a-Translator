package ru.ildus.translator.rx

import ru.ildus.translator.view.FeatureContract
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

//In the sake of testing
class SchedulerProvider : FeatureContract.ISchedulerProvider {

    override fun ui(): Scheduler = AndroidSchedulers.mainThread()

    override fun io(): Scheduler = Schedulers.io()
}
