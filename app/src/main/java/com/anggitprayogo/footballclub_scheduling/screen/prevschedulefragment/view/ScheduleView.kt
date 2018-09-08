package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view

import com.anggitprayogo.footballclub_scheduling.api.repository.ScheduleCallback
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events

interface ScheduleView: ScheduleCallback<Events>{

    fun onProgress()
    fun showError(error: String?)
    fun showResponse(response: String?)
    fun postProgress()
    fun isEmpty()
    fun showData(datas: MutableList<DataEvent>?)

}