package com.anggitprayogo.footballclub_scheduling.screen.detailteams

import android.util.Log.d
import android.util.Log.v
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TeamDetailsPresenter(private val view: TeamDetailView,
                           private val retrofit: ServiceGenerator) {

    private val TAG: String = this.javaClass.simpleName

    fun getTeamDetail(teamId: String){
        view.showLoading()
        var call = retrofit.createClass().getTeamDetail(teamId)
        call.enqueue(object : Callback<Teams>{
            override fun onFailure(call: Call<Teams>?, t: Throwable?) {
                view.hideLoading()
                d(TAG, t?.message)
            }

            override fun onResponse(call: Call<Teams>?, response: Response<Teams>?) {
                view.hideLoading()
                if (response!!.isSuccessful){
                    d(TAG, GsonBuilder().setPrettyPrinting().create().toJson(response?.body()))
                    view.showTeamDetails(response.body()!!.teams)
                }else{
                    d(TAG, "ERROR")
                }
            }

        })

    }

}