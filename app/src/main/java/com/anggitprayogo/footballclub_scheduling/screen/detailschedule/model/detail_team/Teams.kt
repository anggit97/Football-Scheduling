package com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team

import com.google.gson.annotations.SerializedName

data class EventsTeams(
	@SerializedName("teams")
	var teams: List<TeamsItem>
)