package com.anggitprayogo.footballclub_scheduling.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class MyDatabaseOpenHelper(ctx: Context): ManagedSQLiteOpenHelper(ctx, "FavouriteTeam.db", null, 1) {

    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper{
            if (instance == null){
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance as MyDatabaseOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        //Here you can create table
        db?.createTable(
                Favourite.TABLE_FAVOURITE, true,
                Favourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favourite.TEAM_ID to TEXT + UNIQUE,
                Favourite.TEAM_NAME to TEXT,
                Favourite.TEAM_BADGE to TEXT
        )

        db?.createTable(ScheduleTeamFavourite.TABLE_NAME, true,
                ScheduleTeamFavourite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                ScheduleTeamFavourite.SCHEDULE_ID to TEXT + UNIQUE,
                ScheduleTeamFavourite.SCHEDULE_DATE to TEXT,
                ScheduleTeamFavourite.TEAM_HOME to TEXT,
                ScheduleTeamFavourite.TEAM_AWAY to TEXT,
                ScheduleTeamFavourite.TEAM_HOME_SCORE to TEXT,
                ScheduleTeamFavourite.TEAM_AWAY_SCORE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        //Here you can upgrade table as usual
        db?.dropTable(Favourite.TABLE_FAVOURITE, true)
        db?.dropTable(ScheduleTeamFavourite.TABLE_NAME, true)
    }
}

val Context.database: MyDatabaseOpenHelper
get() = MyDatabaseOpenHelper.getInstance(applicationContext)