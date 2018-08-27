package com.anggitprayogo.footballclub_scheduling.screen.detailschedule.view

import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event.EventsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem

interface DetailScheduleView {

    fun onProgress()
    fun postProgress()
    fun showError(error: String?)
    fun showData(data: EventsItem?)
    fun showResults(results: String?)
    fun showTeamResult(results: TeamsItem?, str: String)
    fun isEmpty(size: Int?)

}