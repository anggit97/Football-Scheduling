package com.anggitprayogo.footballclub_scheduling.screen.detailteams

import android.database.sqlite.SQLiteConstraintException
import android.opengl.Visibility
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.R.attr.colorAccent
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.view.Gravity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.data.Favourite
import com.anggitprayogo.footballclub_scheduling.data.database
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.bumptech.glide.Glide
import org.jetbrains.anko.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.support.v4.swipeRefreshLayout
import retrofit2.Retrofit

class TeamActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var presenter: TeamDetailsPresenter
    private lateinit var retrofit: ServiceGenerator

    private var manuItem: Menu? = null
    private var isFavourite: Boolean = false
    private var teamid: String? = null
    private var teambadge: String? = null

    private lateinit var progressBar: ProgressBar
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout

    private lateinit var teamBadge: ImageView
    private lateinit var teamName: TextView
    private lateinit var teamFormedYear: TextView
    private lateinit var teamStadium: TextView
    private lateinit var teamDescription: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        linearLayout {
            lparams(matchParent, matchParent)
            orientation = LinearLayout.VERTICAL
            backgroundColor = android.R.color.white

            swipeRefreshLayout = swipeRefreshLayout {
                setColorSchemeColors(
                        colorAccent,
                        resources.getColor(android.R.color.holo_green_light),
                        resources.getColor(android.R.color.holo_orange_light),
                        resources.getColor(android.R.color.holo_red_light)
                )

                scrollView {
                    isVerticalScrollBarEnabled = false

                    relativeLayout {
                        lparams(width = matchParent, height = wrapContent)

                        linearLayout {
                            lparams(width = matchParent,height = wrapContent)
                            padding = dip(10)
                            orientation = LinearLayout.VERTICAL
                            gravity = Gravity.CENTER_HORIZONTAL

                            teamBadge = imageView { }.lparams(height = dip(75))

                            teamName = textView {
                                gravity = Gravity.CENTER_HORIZONTAL
                                textSize = 20f
                                textColor = ContextCompat.getColor(context, R.color.colorAccent)
                            }.lparams{
                                topMargin = dip(5)
                            }

                            teamFormedYear = textView{
                                this.gravity = Gravity.CENTER
                            }

                            teamStadium = textView{
                                this.gravity = Gravity.CENTER
                                textColor = ContextCompat.getColor(context, R.color.colorAccent)
                            }

                            teamDescription = textView().lparams{
                                topMargin = dip(20)
                            }
                        }

                        progressBar = progressBar {
                        }.lparams {
                            centerHorizontally()
                        }
                    }
                }
            }
        }

        supportActionBar?.title = "Team Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        retrofit = ServiceGenerator()
        presenter = TeamDetailsPresenter(this, retrofit)

        teamid = intent.getStringExtra("id")

        favouriteState()
        setFavourite()
        presenter.getTeamDetail(teamid!!)

        swipeRefreshLayout.setOnRefreshListener {
            presenter.getTeamDetail(teamid!!)
            swipeRefreshLayout.isRefreshing = false
        }

    }

    override fun showLoading() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progressBar.visibility = View.GONE
    }

    override fun showTeamDetails(data: List<TeamsItem>) {

        Glide.with(applicationContext)
                .load(data.get(0).strTeamBadge)
                .into(teamBadge)

        teamName.text = data.get(0).strTeam
        teamFormedYear.text = data.get(0).strAlternate
        teamStadium.text = data.get(0).strStadium
        teamDescription.text = data.get(0).strDescriptionEN

        teambadge = data.get(0).strTeamBadge

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)
        manuItem = menu
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            R.id.add_to_favorite -> {
                if (isFavourite) removeFromFavourite() else addToFavourites()

                isFavourite = !isFavourite

                setFavourite()

                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun addToFavourites(){
        try {
            database.use {
                insert(Favourite.TABLE_FAVOURITE,
                        Favourite.TEAM_ID to teamid,
                        Favourite.TEAM_NAME to teamName.text,
                        Favourite.TEAM_BADGE to teambadge)
            }
            snackbar(swipeRefreshLayout, "Added to favourite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    private fun removeFromFavourite(){
        try {
            database.use {
                delete(Favourite.TABLE_FAVOURITE, "(TEAM_ID = {id})",
                        "id" to teamid!!)
            }
            snackbar(swipeRefreshLayout, "Removed from favourite").show()
        }catch (e: SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }

    private fun setFavourite(){
        if(isFavourite){
            manuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_added_to_favorites)
        }else{
            manuItem?.getItem(0)?.icon = ContextCompat.getDrawable(this, R.drawable.ic_add_to_favorites)
        }
    }

    private fun favouriteState(){
        try{
            database.use {
                val results = select(Favourite.TABLE_FAVOURITE)
                        .whereArgs("(TEAM_ID = {id})",
                                "id" to teamid!!)
                val favourite = results.parseList(classParser<Favourite>())
                if (!favourite.isEmpty()) isFavourite = true
            }
        }catch (e : SQLiteConstraintException){
            snackbar(swipeRefreshLayout, e.localizedMessage).show()
        }
    }
}
