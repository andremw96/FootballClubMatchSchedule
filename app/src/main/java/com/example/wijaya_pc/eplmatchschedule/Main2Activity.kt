package com.example.wijaya_pc.eplmatchschedule

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.wijaya_pc.eplmatchschedule.API.ApiRepository
import com.example.wijaya_pc.eplmatchschedule.Adapter.MatchAdapter
import com.example.wijaya_pc.eplmatchschedule.Adapter.SectionsPagerAdapter
import com.example.wijaya_pc.eplmatchschedule.Model.Match
import com.example.wijaya_pc.eplmatchschedule.Presenter.MainPresenter
import com.example.wijaya_pc.eplmatchschedule.View.MainView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main2.*
import org.jetbrains.anko.find
import org.jetbrains.anko.support.v4.ctx
import org.jetbrains.anko.support.v4.onRefresh

class Main2Activity : AppCompatActivity() {

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        setSupportActionBar(toolbar)
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        // Set up the ViewPager with the sections adapter.
        container.adapter = mSectionsPagerAdapter

        container.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabs))
        tabs.addOnTabSelectedListener(TabLayout.ViewPagerOnTabSelectedListener(container))
    }

    class PlaceholderFragment : Fragment(), MainView {

        private lateinit var listMatch: RecyclerView
        private lateinit var swipeRefresh: SwipeRefreshLayout

        private var matches: MutableList<Match> = mutableListOf()
        private lateinit var presenter: MainPresenter
        private lateinit var adapter: MatchAdapter

        companion object {
            private const val ARG_SECTION_NUMBER = "section_number"
            private const val leagueID = "4328"

            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }

        override fun showMatchList(data: List<Match>) {
            swipeRefresh.isRefreshing = false
            matches.clear()
            matches.addAll(data)
            adapter.notifyDataSetChanged()
        }

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val rootView = inflater.inflate(R.layout.fragment_main2, container, false)
            swipeRefresh = rootView.find(R.id.frag_swipe_refresh) as SwipeRefreshLayout
            listMatch = rootView.find(R.id.frag_rv_match_list) as RecyclerView

            listMatch.layoutManager = LinearLayoutManager(ctx)
            adapter = MatchAdapter(matches) {
                val intent = Intent(ctx, DetailActivity::class.java)
                intent.putExtra(DetailActivity.matchParcel, it)
                startActivity(intent)
            }
            listMatch.adapter = adapter

            val request = ApiRepository()
            val gson = Gson()
            presenter = MainPresenter(this, request, gson)

            if (arguments?.getInt(ARG_SECTION_NUMBER) == 1) {
                presenter.getLast15MatchesList(leagueID)

                swipeRefresh.onRefresh {
                    presenter.getLast15MatchesList(leagueID)
                }
            } else {
                presenter.getNext15MatchesList(leagueID)

                swipeRefresh.onRefresh {
                    presenter.getNext15MatchesList(leagueID)
                }
            }

            return rootView
        }
    }
}
