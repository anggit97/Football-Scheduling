package com.anggitprayogo.footballclub_scheduling.data

data class ScheduleTeamFavourite(val id: Long?, val scheduleId: String?, val scheduleDate: String?, val teamHome: String?, val teamAway: String?, val scoreTeamHome: String?, val scoreTeamAway: String?) {

    companion object {
        const val TABLE_NAME: String = "schedule_team_favourite"
        const val ID: String = "id"
        const val SCHEDULE_ID: String = "schedule_id"
        const val SCHEDULE_DATE: String = "schedule_date"
        const val TEAM_HOME: String = "team_home"
        const val TEAM_HOME_SCORE: String = "team_home_score"
        const val TEAM_AWAY: String = "team_away"
        const val TEAM_AWAY_SCORE: String = "team_away_score"
    }

}