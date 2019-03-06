package com.example.enduser.overwatchleague

interface SubscribeContract{

    interface View{
        fun updateUi(data: ArrayList<OverwatchTeam>, set: HashSet<String>)
        fun setSubscriberMessage()
    }

    interface Presenter{
        fun loadSubcribedContentFromDb()
    }
}