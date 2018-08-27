package com.anggitprayogo.footballclub_scheduling.data

data class ScheduleTeamFavourite(val id: Long?, val scheduleDate: String?, val teamHome: String?, val teamAway: String?, val scoreTeamHome: String?, val scoreTeamAway: String?) {

    companion object {
        const val TABLE_NAME = "schedule_team_favourite.db"
        const val ID = "id"
        const val SCHEDULE_ID = "schedule_id"
        const val SCHEDULE_DATE = "schedule_date"
        const val TEAM_HOME = "team_home"
        const val TEAM_HOME_SCORE = "team_home_score"
        const val TEAM_AWAY = "team_away"
        const val TEAM_AWAY_SCORE = "team_away_score"
    }

}