package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.presenter

import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleCallback
import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleRepository
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.ScheduleView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class PrevSchedulePresenter(val view: ScheduleView,
                            val repostory: ScheduleRepository) {

    fun getPrevSchedule(){
        view.onProgress()
        repostory.getPrevMatch("4328", object : ScheduleCallback<Events?>{
            override fun onDataLoaded(data: Events?) {
                view.postProgress()
                view.onDataLoaded(data)
                async(UI) {
                    val prevSchedule = bg { data?.datas }
                    view.showData(prevSchedule.await())
                }
            }

            override fun onDataError() {
                view.postProgress()
                view.onDataError()
            }

        })
    }

}