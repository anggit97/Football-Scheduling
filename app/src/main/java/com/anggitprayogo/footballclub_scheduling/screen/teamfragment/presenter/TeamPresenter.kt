package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.presenter

import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.view.TeamsView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPresenter(val view: TeamsView,
                    val retrofit: ServiceGenerator,
                    val gson: Gson,
                    val apiRepository: ApiRepository) {

    fun getTeams(leagueName: String){
        view.showLoading()

        var call = retrofit.createClass().getTeamsByLeague("English Premier League")
        call.enqueue(object : Callback<Teams>{
            override fun onFailure(call: Call<Teams>?, t: Throwable?) {
                view.hideLoading()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Teams>?, response: Response<Teams>?) {
                view.hideLoading()
                view.showError(GsonBuilder().setPrettyPrinting().create().toJson(response?.body()))
                view.showTeamList(response?.body()!!.teams)
            }

        })
    }

    fun getTeamsCourutine(league: String){
        view.showLoading()

        async(UI) {
            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getTeams("English Premier League")),
                        Teams::class.java
                )
            }
            view.showTeamList(data.await().teams)
            view.hideLoading()
        }
    }

}