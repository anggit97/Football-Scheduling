package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view

import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent

interface PrevScheduleView {

    fun onProgress()
    fun showError(error: String?)
    fun showResponse(response: String?)
    fun postProgress()
    fun isEmpty()
    fun showData(datas: MutableList<DataEvent>?)

}