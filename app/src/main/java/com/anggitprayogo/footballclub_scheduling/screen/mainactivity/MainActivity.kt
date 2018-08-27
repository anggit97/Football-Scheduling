package com.anggitprayogo.footballclub_scheduling.screen.mainactivity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import com.anggitprayogo.footballclub_scheduling.R
import com.anggitprayogo.footballclub_scheduling.screen.nextschedulefragment.NextScheduleFragment
import com.anggitprayogo.footballclub_scheduling.screen.prevschedulefragment.PrevScheduleFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(){

    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment(PrevScheduleFragment())

        navigation_bottom.setOnNavigationItemSelectedListener(mNavigationBottomListener)
    }

    private val mNavigationBottomListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when(item.itemId){
            R.id.menu_prev ->{
                val prevScheduleFragment = PrevScheduleFragment()
                openFragment(prevScheduleFragment)
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu_next->{
                val nextScheduleFragment = NextScheduleFragment()
                openFragment(nextScheduleFragment)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun openFragment(fragment: Fragment){
        val transation = supportFragmentManager.beginTransaction()
        transation.replace(R.id.container, fragment)
        transation.addToBackStack(null)
        transation.commit()
    }

}
