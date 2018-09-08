package com.anggitprayogo.footballclub_scheduling.screen.detailteams

import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.google.gson.annotations.SerializedName

data class Teams(
        @SerializedName("teams")
        var teams: List<TeamsItem>
)