package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.ui

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.model.DataEvent
import org.jetbrains.anko.AnkoContext

class PrevScheduleAdapter(var events: MutableList<DataEvent>?, val listener: (DataEvent) -> Unit)
    : RecyclerView.Adapter<PrevSchedulerViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PrevSchedulerViewHolder {
        return PrevSchedulerViewHolder(PrevScheduleUI().createView(AnkoContext.Companion.create(parent.context, parent)))
    }

    override fun getItemCount(): Int = events!!.size

    override fun onBindViewHolder(holder: PrevSchedulerViewHolder, position: Int) {
        holder.bindItems(events!!.get(position),listener)
    }

}

class PrevSchedulerViewHolder(view: View): RecyclerView.ViewHolder(view){

    val tvDate: TextView = view.findViewById(R.id.tv_date)
    val tvHomeTeam: TextView = view.findViewById(R.id.tv_home_team)
    val tvAwayTeam: TextView = view.findViewById(R.id.tv_away_team)
    val tvHomeTeamScore: TextView = view.findViewById(R.id.tv_home_team_score)
    val tvAwayTeamScore: TextView = view.findViewById(R.id.tv_away_team_score)

    fun bindItems(dataEvent: DataEvent, listener: (DataEvent) -> Unit){
        tvDate.text = dataEvent.dateEvent
        tvHomeTeam.text = dataEvent.strHomeAway
        tvAwayTeam.text = dataEvent.strAwayTeam
        tvHomeTeamScore.text = dataEvent.intHomeScore
        tvAwayTeamScore.text = dataEvent.intAwayScore

        itemView.setOnClickListener {
            listener(dataEvent)
        }
    }

}