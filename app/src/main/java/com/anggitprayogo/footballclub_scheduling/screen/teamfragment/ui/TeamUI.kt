package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.ui

import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.anggitprayogo.footballclub_scheduling.R
import org.jetbrains.anko.*
import org.jetbrains.anko.cardview.v7.cardView

class TeamUI: AnkoComponent<ViewGroup>{

    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {

        cardView {
            lparams(width = matchParent, height = wrapContent)

            linearLayout {
                lparams(width = matchParent, height = matchParent)
                orientation = LinearLayout.HORIZONTAL
                weightSum = 12f

                imageView {
                    id = R.id.iv_team
                }.lparams(width = dip(0), height = matchParent){
                    weight = 4f
                }

                textView {
                    id = R.id.tv_team
                }.lparams(width = dip(0), height =  matchParent){
                    weight = 8f
                    leftMargin = dip(10)
                }
            }.lparams(){
                margin = dip(10)
            }
        }

    }
}