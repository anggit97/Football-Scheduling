package com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment.presenter

import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi.getNextSchedule
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.Events
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.view.PrevScheduleView
import com.google.gson.Gson
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.async
import org.jetbrains.anko.coroutines.experimental.bg
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NextSchedulePresenter(val view: PrevScheduleView,
                            val retrofit: ServiceGenerator,
                            val gson: Gson,
                            val apiRepository: ApiRepository){


    fun getNextSchedules(){
        view.onProgress()

        var call = retrofit.createClass().getNextSchedules("4328")

        call.enqueue(object : Callback<Events>{

            override fun onFailure(call: Call<Events>?, t: Throwable?) {
                view.postProgress()
                view.showError(t?.message)
            }

            override fun onResponse(call: Call<Events>?, response: Response<Events>?) {
                view.postProgress()

                view.showData(response?.body()?.datas)
            }

        })
    }

    fun getNextSchedulesCoroutine(){
        view.onProgress()

        async(UI) {
            val data = bg {
                gson.fromJson(
                        apiRepository.doRequest(TheSportDBApi.getNextSchedule("4328")),
                        Events::class.java
                )
            }

            view.showData(data.await().datas)
            view.postProgress()
        }
    }

}