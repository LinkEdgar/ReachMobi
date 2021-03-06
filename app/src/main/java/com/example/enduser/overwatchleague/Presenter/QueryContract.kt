package com.example.enduser.overwatchleague.Presenter

import com.example.enduser.overwatchleague.POJOs.OverwatchTeam

/**
 * Created by EndUser on 2/16/2019.
 */

interface QueryContract{
    interface View{
        fun updateUi(data: ArrayList<OverwatchTeam>)
        fun updateUi(team: OverwatchTeam)
        fun setProgressBar()
    }

    interface Presenter{
        fun onSubmitQuery(query: String)
        fun startTeamLoad()
        fun onSubCheckBoxClicked(position: Int, team: OverwatchTeam)
        fun onClearSearch()
    }
}