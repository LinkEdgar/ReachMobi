package com.example.enduser.overwatchleague.Views

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import com.example.enduser.overwatchleague.*
import com.example.enduser.overwatchleague.POJOs.OverwatchTeam
import com.example.enduser.overwatchleague.Presenter.SubscribeContract
import com.example.enduser.overwatchleague.Presenter.SubscribePresenter
import com.example.enduser.overwatchleague.Views.Adapters.SubscribedAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), SubscribeContract.View, SubscribedAdapter.OnClickCallback {

    private lateinit var mRecyclerView: RecyclerView
    private lateinit var mSubscribedAdapter: SubscribedAdapter
    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mData: ArrayList<OverwatchTeam>

    private lateinit var mPresenter: SubscribePresenter
    private lateinit var mTeamHashSet: HashSet<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        mPresenter = SubscribePresenter(this, this)
    }

    override fun onStart() {
        super.onStart()
        mPresenter.loadSubscribedContentFromDb()
    }

    private fun initUi(){
        switchButton.setOnClickListener{switchActivity()}
        mRecyclerView = rv_user_subs
        mData = ArrayList()
        mSubscribedAdapter = SubscribedAdapter(this, mData, this)
        mLayoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mSubscribedAdapter
        mRecyclerView.layoutManager = mLayoutManager
        mTeamHashSet = HashSet()
    }

    override fun updateUi(data: ArrayList<OverwatchTeam>, set: HashSet<String>) {
        var mainHandler = Handler(Looper.getMainLooper())

        var myRunnable =
                Runnable {
                    mData.clear()
                    mData.addAll(data)
                    mSubscribedAdapter.notifyDataSetChanged()
                    mTeamHashSet.clear()
                    mTeamHashSet.addAll(set)
                }
        mainHandler.post(myRunnable)
    }

    override fun setSubscriberMessage(boolean: Boolean) {
        var mainHandler = Handler(Looper.getMainLooper())

        var myRunnable =
                Runnable {
                    if(boolean == true){
                        tv_no_subs.visibility = View.VISIBLE
                    }else{
                        tv_no_subs.visibility = View.GONE
                    }
                }
        mainHandler.post(myRunnable)
    }

    private fun switchActivity(){
        val switch = Intent(this, QueryActivity::class.java)
        switch.putExtra("set",mTeamHashSet)
        startActivity(switch)
    }

    override fun onClick(url: String?) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        }
    }

}
