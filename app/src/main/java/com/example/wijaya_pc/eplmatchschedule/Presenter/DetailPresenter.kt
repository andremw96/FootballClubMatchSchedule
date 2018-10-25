package com.example.wijaya_pc.eplmatchschedule.Presenter

import com.example.wijaya_pc.eplmatchschedule.API.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.API.TheSportDBApi
import com.example.wijaya_pc.eplmatchschedule.Model.MatchResponse
import com.example.wijaya_pc.eplmatchschedule.Model.TeamResponse
import com.example.wijaya_pc.eplmatchschedule.View.DetailView
import com.google.gson.Gson
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class DetailPresenter(
    private val view: DetailView,
    private val apiRepository: ApiRepository,
    private val gson: Gson
) {

    fun getMatchDetail(matchID: String?) {
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getMatchDetail(matchID)),
                MatchResponse::class.java
            )

            uiThread {
                view.getMatch(data.events[0])
            }
        }
    }

    fun getTeamDetail(teamID: String?, homeTeam: Boolean) {
        doAsync {
            val data = gson.fromJson(
                apiRepository.doRequest(TheSportDBApi.getTeam(teamID)),
                TeamResponse::class.java
            )

            uiThread {
                view.getTeam(data.teams[0], homeTeam)
            }
        }

    }
}