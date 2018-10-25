package com.example.wijaya_pc.eplmatchschedule

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ImageView
import android.widget.TextView
import com.example.wijaya_pc.eplmatchschedule.API.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.Model.Match
import com.example.wijaya_pc.eplmatchschedule.Model.Team
import com.example.wijaya_pc.eplmatchschedule.Presenter.DetailPresenter
import com.example.wijaya_pc.eplmatchschedule.R.id.*
import com.example.wijaya_pc.eplmatchschedule.UI.DetailUI
import com.example.wijaya_pc.eplmatchschedule.View.DetailView
import com.google.gson.Gson
import com.squareup.picasso.Picasso
import org.jetbrains.anko.find
import org.jetbrains.anko.setContentView
import java.text.SimpleDateFormat


class DetailActivity : AppCompatActivity(), DetailView {

    companion object {
        const val matchParcel = "match_parcel"
    }

    private lateinit var detailPresenter: DetailPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        DetailUI().setContentView(this)

        val intent = intent
        val dataMatchParcel = intent.getParcelableExtra<Match>(matchParcel)

        val request = ApiRepository()
        val gson = Gson()
        detailPresenter = DetailPresenter(this, request, gson)

        detailPresenter.getMatchDetail(dataMatchParcel.matchId)

        detailPresenter.getTeamDetail(dataMatchParcel.idHomeTeam, true)
        detailPresenter.getTeamDetail(dataMatchParcel.idAwayTeam, false)
    }

    override fun getMatch(data: Match) {
        val matchDate: TextView = find(match_date)
        matchDate.text = (SimpleDateFormat("EEE, dd MMM yyyy").format(data.matchDate)).toString()

        val hometeam: TextView = find(match_home_team)
        val awayteam: TextView = find(match_away_team)
        hometeam.text = data.homeTeam
        awayteam.text = data.awayTeam

        val homescore: TextView = find(match_home_score)
        val awayscore: TextView = find(match_away_score)
        homescore.text = data.homeScore
        awayscore.text = data.awayScore

        val homegoals: TextView = find(match_home_goals)
        val awaygoals: TextView = find(match_away_goals)
        homegoals.text = data.homeGoals
        awaygoals.text = data.awayGoals

        val homeshots: TextView = find(match_home_shots)
        val awayshots: TextView = find(match_away_shots)
        homeshots.text = data.homeShots
        awayshots.text = data.awayShots

        val homeformation: TextView = find(match_home_formation)
        val awayformation: TextView = find(match_away_formation)
        homeformation.text = data.homeFormation
        awayformation.text = data.awayFormation

        val homegoalkeeper: TextView = find(match_home_goalkeeper)
        val awaygoalkeeper: TextView = find(match_away_goalkeeper)
        homegoalkeeper.text = data.homeGoalKeeper
        awaygoalkeeper.text = data.awayGoalKeeper

        val homedefense: TextView = find(match_home_defense)
        val awaydefense: TextView = find(match_away_defense)
        homedefense.text = data.homeDefence
        awaydefense.text = data.awayDefence

        val homemidfield: TextView = find(match_home_midfield)
        val awaymidfield: TextView = find(match_away_midfield)
        homemidfield.text = data.homeMidfield
        awaymidfield.text = data.awayMidfield

        val homeforward: TextView = find(match_home_forward)
        val awayforward: TextView = find(match_away_forward)
        homeforward.text = data.homeForward
        awayforward.text = data.awayForward

        val homesubs: TextView = find(match_home_subs)
        val awaysubs: TextView = find(match_away_subs)
        homesubs.text = data.homeSubstitutes
        awaysubs.text = data.awaySubtitutes
    }


    override fun getTeam(data: Team, homeTeam: Boolean) {
        val homelogo: ImageView = find(home_logo)
        val awaylogo: ImageView = find(away_logo)
        Picasso.get().load(data.teamBadge).into(if (homeTeam == true) homelogo else awaylogo)
    }

}
