package com.anggitprayogo.footballclub_scheduling.screen.schedulefavouritefragment

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.data.ScheduleTeamFavourite
import com.anggitprayogo.footballclub_scheduling.screen.favouriteteamsfragment.FavouriteViewHolder
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class ScheduleFavouriteAdapter(val schedules: List<ScheduleTeamFavourite>,
                               val listener: (ScheduleTeamFavourite) -> Unit): RecyclerView.Adapter<ScheduleFavouriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleFavouriteViewHolder {
       return ScheduleFavouriteViewHolder(ScheduleUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = schedules.size

    override fun onBindViewHolder(holder: ScheduleFavouriteViewHolder, position: Int) {
        holder.bindItems(schedules.get(position), listener);
    }

}

class ScheduleFavouriteViewHolder(view: View): RecyclerView.ViewHolder(view){

    var scheduleDate = view.findViewById<TextView>(R.id.schedule_date)
    var teamHome = view.findViewById<TextView>(R.id.team_home)
    var teamAway = view.findViewById<TextView>(R.id.team_away)
    var teamHomeScoe = view.findViewById<TextView>(R.id.team_home_score)
    var teamAwayScore = view.findViewById<TextView>(R.id.team_away_score)

    fun bindItems(get: ScheduleTeamFavourite, listener: (ScheduleTeamFavourite) -> Unit) {

        scheduleDate.text = get.scheduleDate
        teamHome.text = get.teamHome
        teamAway.text = get.teamAway
        teamHomeScoe.text = get.scoreTeamHome
        teamAwayScore.text = get.scoreTeamAway

        listener(get)

    }

}

class ScheduleUI: AnkoComponent<ViewGroup>{

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui){

        cardView {
            lparams(width = matchParent, height = wrapContent){
                margin = dip(16)
            }

            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.VERTICAL
                gravity = Gravity.CENTER_HORIZONTAL

                textView {
                    id = R.id.schedule_date
                    text = "12 Januari 2018"
                    textSize = 16f
                    textColor = android.R.color.black
                }.lparams(width = matchParent, height = wrapContent){
                    topMargin = dip(10)
                    bottomMargin = dip(5)
                }

                linearLayout {
                    lparams(width = matchParent, height = wrapContent)
                    orientation = LinearLayout.HORIZONTAL
                    gravity = Gravity.CENTER_HORIZONTAL
                    weightSum = 12f

                    textView {
                        id = R.id.team_home
                        text = "MU"
                        textSize = 14f
                        textColor = android.R.color.holo_red_light
                    }.lparams(width = 0, height = wrapContent){
                        weight = 4.5f
                    }

                    textView {
                        id = R.id.team_home_score
                        text = "2"
                        textSize = 14f
                        textColor = android.R.color.holo_red_light
                    }.lparams(width = 0, height = wrapContent){
                        weight = 1f
                    }

                    textView {
                        text = "VS"
                        textSize = 14f
                        textColor = android.R.color.holo_red_light
                    }.lparams(width = 0, height = wrapContent){
                        weight = 1f
                    }

                    textView {
                        id = R.id.team_away_score
                        text = "3"
                        textSize = 14f
                        textColor = android.R.color.holo_red_light
                    }.lparams(width = 0, height = wrapContent){
                        weight = 1f
                    }

                    textView {
                        id = R.id.team_away
                        text = "CHELSEA"
                        textSize = 14f
                        textColor = android.R.color.holo_red_light
                    }.lparams(width = 0, height = wrapContent){
                        weight = 4.5f
                    }
                }

            }
        }

    }

}