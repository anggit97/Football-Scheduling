package com.anggitprayogo.footballclub_scheduling.api.repository

import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events

interface ScheduleCallback <T>{
    fun onDataLoaded(data: Events?)
    fun onDataError()

}