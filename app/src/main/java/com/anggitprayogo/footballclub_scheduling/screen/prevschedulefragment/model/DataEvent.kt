package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model

import com.google.gson.annotations.SerializedName

data class DataEvent(
        @SerializedName("idEvent") var idEvent: String,
        @SerializedName("strHomeTeam") var strHomeAway: String,
        @SerializedName("strAwayTeam") var strAwayTeam: String,
        @SerializedName("intHomeScore") var intHomeScore: String,
        @SerializedName("intAwayScore") var intAwayScore: String,
        @SerializedName("strHomeGoalDetails") var strHomeGoalDetails: String,
        @SerializedName("strAwayGoalDetails") var strAwayGoalDetails: String,
        @SerializedName("dateEvent") var dateEvent: String
)