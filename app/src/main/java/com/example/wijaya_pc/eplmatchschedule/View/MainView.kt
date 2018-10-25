package com.example.wijaya_pc.eplmatchschedule.View

import com.example.wijaya_pc.eplmatchschedule.Model.Match

interface MainView {
    fun showMatchList(data: List<Match>)
}