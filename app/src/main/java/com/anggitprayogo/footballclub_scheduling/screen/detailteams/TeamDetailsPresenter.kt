package com.anggitprayogo.footballclub_scheduling.screen.detailteams

import android.util.Log.d
import android.util.Log.v
import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit

class TeamDetailsPresenter(private val view: TeamDetailView,
                           private val retrofit: ServiceGenerator,
                           private val gson: Gson,
                           private val apiRepository: ApiRepository) {

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

//    async(UI) {
//        val data = bg {
//            gson.fromJson(
//                    apiRepository.doRequest(TheSportDBApi.getTeams("English Premier League")),
//                    Teams::class.java
//            )
//        }
//        view.showTeamList(data.await().teams)
//        view.hideLoading()
//    }

    fun getTeamDetailCourutine(teamId: String){
        view.showLoading()

        async(UI){
            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getTeamDetail(teamId)),
                        Teams::class.java
                )
            }

            view.showTeamDetails(data.await().teams)
            view.hideLoading()
        }
    }

}