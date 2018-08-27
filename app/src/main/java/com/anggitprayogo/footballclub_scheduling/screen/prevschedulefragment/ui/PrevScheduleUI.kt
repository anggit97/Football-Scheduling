package com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.ui

import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import com.anggitprayogo.footballclub_scheduling.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class PrevScheduleUI: AnkoComponent<ViewGroup>{

    override fun createView(ui: AnkoContext<ViewGroup>) = with(ui) {

        cardView {
            lparams(matchParent, wrapContent){
                bottomMargin = 10
            }

            linearLayout {
                lparams(matchParent, matchParent){
                    margin = 10
                }
                orientation = LinearLayout.VERTICAL
                padding = 5

                textView(){
                    id = R.id.tv_date
                    text = "Tanggal Pertandingan"
                    gravity = Gravity.CENTER
                }.lparams(matchParent, wrapContent)

                linearLayout {
                    lparams(matchParent, matchParent){
                        bottomMargin = 10
                        topMargin = 10
                    }
                    orientation = LinearLayout.HORIZONTAL
                    weightSum = 12f

                    textView(){
                        id = R.id.tv_home_team
                        text = "MU"
                        gravity = LinearLayout.TEXT_ALIGNMENT_CENTER
                    }.lparams(0, matchParent){
                        weight = 4.5f
                    }


                    textView(){
                        id = R.id.tv_home_team_score
                        text = "2"
                    }.lparams(0, matchParent){
                        weight = 1f
                    }


                    textView(){
                        id = R.id.tv_vs
                        text = "VS"
                    }.lparams(0, matchParent){
                        weight = 1f
                    }

                    textView(){
                        id = R.id.tv_away_team_score
                        text = "1"
                    }.lparams(0, matchParent){
                        weight = 1f
                    }

                    textView(){
                        id = R.id.tv_away_team
                        text = "CHELSEA"
                        gravity = LinearLayout.TEXT_ALIGNMENT_CENTER
                    }.lparams(0, matchParent){
                        weight = 4.5f
                    }


                }

            }

        }
    }

}