package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.presenter

import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.view.TeamsView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TeamPresenter(val view: TeamsView,
                    val retrofit: ServiceGenerator) {

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

}