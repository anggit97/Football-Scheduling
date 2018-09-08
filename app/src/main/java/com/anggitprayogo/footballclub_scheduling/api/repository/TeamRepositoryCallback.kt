package com.anggitprayogo.footballclub_scheduling.api.repository

interface TeamRepositoryCallback <T>{

    fun onDataLoaded(data: T?)
    fun onDataError()

}