package com.kutugondrong.kutugondronggithub.activity.main.view

import android.os.Bundle
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kutugondrong.kutugondronggithub.R
import com.kutugondrong.kutugondronggithub.activity.main.viewmodel.MainViewModel
import com.kutugondrong.kutugondronggithub.adapter.MainAdapter
import com.kutugondrong.kutugondronggithub.custom.EndlessRecyclerViewScrollListener
import com.kutugondrong.kutugondronggithub.model.User
import com.kutugondrong.kutugondronggithub.network.helper.Status
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel : MainViewModel by viewModels()
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var adapter: MainAdapter
    private lateinit var scrollListener: EndlessRecyclerViewScrollListener


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initUi()
        fetchUser()
    }

    private fun initUi() {
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
                DividerItemDecoration(
                        recyclerView.context,
                        (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
        )
        recyclerView.adapter = adapter
        txtSearch.setOnEditorActionListener(OnEditorActionListener { v, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mainViewModel.searchUser(txtSearch.text.toString())
                return@OnEditorActionListener true
            }
            false
        })

        scrollListener = object : EndlessRecyclerViewScrollListener(linearLayoutManager) {
            override fun onLoadMore(page: Int, totalItemsCount: Int, view: RecyclerView?) {
                showEndLessScroll()
            }
        }

        recyclerView.addOnScrollListener(scrollListener)
    }


    private fun showEndLessScroll() {
        Toast.makeText(this, "Sudah di akhir", Toast.LENGTH_LONG).show()
    }

    private fun fetchUser() {
        mainViewModel.users.observe(this, Observer {
            when (it.status) {
                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    it.data?.let { data ->
                        addData(data.users)
                        scrollListener?.resetState()
                    }
                    recyclerView.visibility = View.VISIBLE
                }
                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                    recyclerView.visibility = View.GONE
                }
                Status.ERROR -> {
                    //Handle Error
                    progressBar.visibility = View.GONE
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    scrollListener?.resetState()
                }
            }
        })
    }

    private fun addData(users: List<User>) {
        adapter.addData(users)
        adapter.notifyDataSetChanged()
    }
}
