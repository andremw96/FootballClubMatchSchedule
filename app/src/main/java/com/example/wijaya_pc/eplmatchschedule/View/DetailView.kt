package com.example.wijaya_pc.eplmatchschedule.View

import com.example.wijaya_pc.eplmatchschedule.Model.Match
import com.example.wijaya_pc.eplmatchschedule.Model.Team

interface DetailView {
    fun getMatch(data: Match)
    fun getTeam(data: Team, homeTeam: Boolean)

}