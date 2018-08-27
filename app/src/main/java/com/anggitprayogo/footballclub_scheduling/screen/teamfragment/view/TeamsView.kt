package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.view

import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem

interface TeamsView {

    fun showLoading()
    fun hideLoading()
    fun showTeamList(data: List<TeamsItem>)
    fun showError(error: String?)

}