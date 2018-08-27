package com.anggitprayogo.footballclub_scheduling.screen.detailschedule

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log.d
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.constant.Constant
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event.EventsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.presenter.DetailSchedulePresenter
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.view.DetailScheduleView
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_detail_schedule.*
import org.jetbrains.anko.toast

class DetailScheduleActivity : AppCompatActivity(), DetailScheduleView{


    var response: MutableList<DataEvent>? = mutableListOf()
    lateinit var retrofit: ServiceGenerator
    lateinit var presenter: DetailSchedulePresenter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var id: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_schedule)

        id = intent.getStringExtra(Constant.ID_EVENT)

        retrofit = ServiceGenerator()
        presenter = DetailSchedulePresenter(this, retrofit, id)

        presenter.getDetailEvent()

        swipe_refesh_layout.setOnRefreshListener {
            presenter.getDetailEvent()
        }
    }

    override fun onProgress() {
        swipe_refesh_layout.isRefreshing = true
    }

    override fun postProgress() {
        swipe_refesh_layout.isRefreshing = false
    }

    override fun showError(error: String?) {
        toast(error!!)
    }

    override fun showData(data: EventsItem?) {


        tv_date.text = data?.dateEvent
        tv_score_home.text = data?.intHomeScore
        tv_score_away.text = data?.intAwayScore
        tv_goal_home.text = data?.intHomeScore
        tv_goal_Away.text = data?.intAwayScore
        tv_team_home.text = data?.strHomeTeam
        tv_team_away.text = data?.strAwayTeam
        tv_team_formation_home.text = data?.strHomeFormation
        tv_team_formation_away.text = data?.strAwayFormation

        presenter.getDetailTeam(data?.idHomeTeam!!,"home")
        presenter.getDetailTeam(data?.idAwayTeam!!,"away")

        d("HASIL :",data?.dateEvent)
    }

    override fun showTeamResult(results: TeamsItem?, str: String) {


        if (str.equals("home")){
            Glide.with(this)
                    .load(results?.strTeamBadge)
                    .into(iv_team_home)
        }else{
            Glide.with(this)
                    .load(results?.strTeamBadge)
                    .into(iv_team_away)
        }
    }

    override fun showResults(results: String?) {
        d("RESULT : ",results)
    }

    override fun isEmpty(size: Int?) {
        d("KOSONG", size.toString())
    }
}
