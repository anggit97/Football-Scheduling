package com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event

import com.google.gson.annotations.SerializedName

data class Response(

	@SerializedName("events")
	var events: List<EventsItem?>? = null
)