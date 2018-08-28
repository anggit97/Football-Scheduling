package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.presenter

import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PrevSchedulePresenter(val view: PrevScheduleView,
                            val retrofit: ServiceGenerator,
                            val gson: Gson,
                            val apiRepository: ApiRepository) {

    fun getSchedules(){
        view.onProgress()



        var call = retrofit.createClass().getPrevSchedules("4328")

        call.enqueue(object : Callback<Events>{

            override fun onFailure(call: Call<Events>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                var gson = GsonBuilder().setPrettyPrinting().create().toJson(response?.body())

                if (response!!.isSuccessful){
                    view.showResponse(""+gson)
                    view.showData(response?.body()?.datas)
                }else{
                    view.showResponse("Gagal")
                }

            }

        })
    }


    fun getSchedulesCouroutine(){
        view.onProgress()

        async(UI) {
            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getPastSchedule("4328")),
                        Events::class.java
                )
            }

            view.showData(data.await().datas)
            view.postProgress()
        }
    }

}