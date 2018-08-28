package com.anggitprayogo.footballclub_scheduling.screen.detailschedule

import android.database.sqlite.SQLiteConstraintException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log.d
import android.view.Menu
import android.view.MenuItem
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.constant.Constant
import com.anggitprayogo.footballclub_scheduling.data.ScheduleTeamFavourite
import com.anggitprayogo.footballclub_scheduling.data.database
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_event.EventsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.presenter.DetailSchedulePresenter
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.view.DetailScheduleView
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import com.bumptech.glide.Glide
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_schedule.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.toast

class DetailScheduleActivity : AppCompatActivity(), DetailScheduleView{


    var response: MutableList<DataEvent>? = mutableListOf()
    lateinit var retrofit: ServiceGenerator
    lateinit var presenter: DetailSchedulePresenter
    lateinit var swipeRefreshLayout: SwipeRefreshLayout
    lateinit var id: String

    private var menuItem: Menu? = null
    private var isFavourite: Boolean  = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_schedule)

        id = intent.getStringExtra(Constant.ID_EVENT)

        swipeRefreshLayout = swipe_refesh_layout

        var gson = Gson()
        var request = ApiRepository()

        retrofit = ServiceGenerator()
        presenter = DetailSchedulePresenter(this, retrofit, id, gson, request)

        presenter.getDetailEventCoroutine()

        getFavouriteState()

        swipe_refesh_layout.setOnRefreshListener {
            presenter.getDetailEventCoroutine()
        }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Schedule Detail"
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        menuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when (item!!.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavourite) removeFromFavourite() else addToFavourite()

                getFavouriteState()

                setFavouriteState()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addToFavourite(){
        try {
            database.use {
                insert(ScheduleTeamFavourite.TABLE_NAME,
                        ScheduleTeamFavourite.SCHEDULE_ID to id,
                        ScheduleTeamFavourite.TEAM_AWAY_SCORE to tv_score_away.text,
                        ScheduleTeamFavourite.TEAM_AWAY to tv_team_away.text,
                        ScheduleTeamFavourite.TEAM_HOME to tv_team_home.text,
                        ScheduleTeamFavourite.TEAM_HOME_SCORE to tv_score_home.text,
                        ScheduleTeamFavourite.SCHEDULE_DATE to tv_date.text)
            }
            snackbar(swipeRefreshLayout, "Added to favourite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    fun removeFromFavourite(){
        try{
            database.use {
                delete(ScheduleTeamFavourite.TABLE_NAME,
                        "(schedule_id = {id})",
                        "id" to id)
            }
            snackbar(swipeRefreshLayout, "Removed from favourite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage)
        }
    }

    fun getFavouriteState(){
        try{
            database.use {
                var results = select(ScheduleTeamFavourite.TABLE_NAME)
                        .whereArgs("(schedule_id = {id})", "id" to id)
                val favourites = results.parseList(classParser<ScheduleTeamFavourite>())
                if (favourites.isEmpty()) isFavourite = false else isFavourite = true
            }

            setFavouriteState()
        }catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage)
        }
    }

    fun setFavouriteState(){
        if (this!!.isFavourite!!) {
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_added_to_favorites)
        }else{
            menuItem?.getItem(0)?.icon = ContextCompat.getDrawable(applicationContext, R.drawable.ic_add_to_favorites)
        }
    }
}
