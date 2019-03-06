package com.example.enduser.overwatchleague

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide

class SubscribedAdapter(var context: Context,var mData: ArrayList<OverwatchTeam>): RecyclerView.Adapter<SubscribedAdapter.SubscribeViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): SubscribeViewHolder {
        return SubscribeViewHolder(LayoutInflater.from(context).inflate(R.layout.team_container, parent, false))
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: SubscribeViewHolder, position: Int) {
        val team = mData[position]
        holder.mTeamName.text = team.teamName
        Glide.with(holder.mTeamImage).load(team.teamIcon).into(holder.mTeamImage)
        try{
            holder.mTeamName.setTextColor(Color.parseColor(team.teamPrimaryColor))
            //holder.mTeamSub.setHintTextColor(Color.parseColor(team.teamSecondaryColor))
        }catch(a: IllegalArgumentException){
            a.printStackTrace()
        }
        holder.mTeamWins.text = team.matchWin
        holder.mTeamLoss.text = team.matchLoss
        holder.mTeamDraw.text = team.matchDraw
    }

    class SubscribeViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val mTeamName: TextView = itemView.findViewById(R.id.tv_team_name)
        val mTeamImage: ImageView = itemView.findViewById(R.id.iv_team_image)
        val mTeamWins: TextView = itemView.findViewById(R.id.tv_team_win)
        val mTeamLoss: TextView = itemView.findViewById(R.id.tv_team_loss)
        val mTeamDraw: TextView = itemView.findViewById(R.id.tv_team_draw)
    }
}