package com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment.presenter

import android.util.Log
import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleCallback
import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleRepository
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.ScheduleView
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg

class NextSchedulePresenter(val view: ScheduleView,
                            val repository: ScheduleRepository){


    fun getNextSchedules(){
        view.onProgress()

        repository.getNextMatch("4328", object: ScheduleCallback<Events?>{
            override fun onDataLoaded(data: Events?) {
                view.postProgress()
                view.onDataLoaded(data)
                async(UI){
                    val nextSchedule = bg { data?.datas }
                    Log.e("data","coba 2 next"+nextSchedule)
                    view.showData(nextSchedule.await())
                }
            }

            override fun onDataError() {
                view.postProgress()
                view.onDataError()
            }

        })

    }


}