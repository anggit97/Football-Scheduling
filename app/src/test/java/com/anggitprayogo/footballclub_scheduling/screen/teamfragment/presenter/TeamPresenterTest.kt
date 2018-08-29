package com.anggitprayogo.footballclub_scheduling.screen.teamfragment.presenter

import android.net.Uri
import com.anggitprayogo.footballclub_scheduling.api.ApiRepository
import com.anggitprayogo.footballclub_scheduling.api.TheSportDBApi
import com.anggitprayogo.footballclub_scheduling.network.ServiceGenerator
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.Teams
import com.anggitprayogo.footballclub_scheduling.screen.detailschedule.model.detail_team.TeamsItem
import com.anggitprayogo.footballclub_scheduling.screen.teamfragment.view.TeamsView
import com.anggitprayogo.footballclub_scheduling.util.TestContextProvider
import com.google.gson.Gson
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations

class TeamPresenterTest {

    @Mock
    private
    lateinit var view: TeamsView

    @Mock
    private
    lateinit var gson: Gson

    @Mock
    private
    lateinit var apiRepository: ApiRepository

    @Mock
    private
    lateinit var retrofit: ServiceGenerator

    @Mock lateinit var uri: Uri

    private lateinit var presenter: TeamPresenter

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        presenter = TeamPresenter(view, retrofit, gson, apiRepository, TestContextProvider())
    }

    @Test
    fun getTeamsCourutine() {
        val teams: MutableList<TeamsItem> = mutableListOf()
        val response = Teams(teams)
        val league = "English Premiere League"


        `when`(gson.fromJson(apiRepository.doRequest(TheSportDBApi.getTeams(league)), Teams::class.java)).thenReturn(response)

        presenter.getTeamsCourutine(league)

        verify(view).showLoading()
        verify(view).showTeamList(teams)
        verify(view).hideLoading()
    }

}
