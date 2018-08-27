package com.anggitprayogo.footballclub_scheduling.screen.detailteams

import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem

interface TeamDetailView {

    fun showLoading()
    fun hideLoading()
    fun showTeamDetails(data: List<TeamsItem>)

}