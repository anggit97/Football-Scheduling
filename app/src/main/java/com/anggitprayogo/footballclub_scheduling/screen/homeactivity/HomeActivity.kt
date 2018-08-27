package com.anggitprayogo.footballclub_scheduling.screen.homeactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.R.id.favorites
import com.anggitprayogo.footballclub_scheduling.R.id.teams
import com.anggitprayogo.footballclub_scheduling.screen.favouriteteamsfragment.FavoriteTeamsFragment
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.TeamsFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        bottom_navigation.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                teams->{
                    loadTeamsFragment(savedInstanceState)
                }
                favorites->{
                    loadFavouriteTeamsFragment(savedInstanceState)
                }
            }
            true
        }

        bottom_navigation.selectedItemId = teams
    }

    private fun loadTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null){
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, TeamsFragment(), TeamsFragment::class.java.simpleName)
                    .commit()
        }
    }

    private fun loadFavouriteTeamsFragment(savedInstanceState: Bundle?){
        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.main_container, FavoriteTeamsFragment(), FavoriteTeamsFragment::class.java.simpleName)
                    .commit()
        }
    }
}
