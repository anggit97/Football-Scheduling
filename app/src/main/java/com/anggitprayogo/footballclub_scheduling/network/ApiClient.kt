package com.anggitprayogo.footballclub_scheduling.network

import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event.Response
import com.anggitprayogo.footballclub_scheduling.screen.detailteams.Teams
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface ApiClient {

    @Headers("Accept: application/json")
    @GET("api/v1/json/1/eventspastleague.php")
    fun getPrevSchedules(@Query("id") id: String): Call<Events>

    @Headers("Accept: application/json")
    @GET("api/v1/json/1/eventsnextleague.php")
    fun getNextSchedules(@Query("id") id: String): Call<Events>

    @Headers("Accept: application/json")
    @GET("api/v1/json/1/lookupevent.php")
    fun getDetailSchedule(@Query("id") id: String): Call<Response>

    @Headers("Accept: application/json")
    @GET("api/v1/json/1/lookupteam.php")
    fun getTeamDetail(@Query("id") id: String): Call<Teams>

    @Headers("Accept: application/json")
    @GET("api/v1/json/1/search_all_teams.php")
    fun getTeamsByLeague(@Query("l") teams: String): Call<Teams>
}