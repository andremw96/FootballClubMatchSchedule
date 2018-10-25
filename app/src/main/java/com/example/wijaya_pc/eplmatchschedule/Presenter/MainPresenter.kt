package com.example.wijaya_pc.eplmatchschedule.Presenter

import com.example.wijaya_pc.eplmatchschedule.API.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.API.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.Model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.View.MainView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class MainPresenter(
    private val view: MainView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {
    fun getLast15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))

        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getLast15Matches(leagueId)),
                MatchResponse::class.java
            )
            // Log.e("the data", "$data")

            uiThread {
                view.showMatchList(data.events)
            }
        }
    }

    fun getNext15MatchesList(leagueId: String?) {
        //Log.d("linknya", TheSportDBApi.getLast15Matches(leagueId))

        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getNext15Matches(leagueId)),
                MatchResponse::class.java
            )
            // Log.e("the data", "$data")

            uiThread {
                view.showMatchList(data.events)
            }
        }
    }
}