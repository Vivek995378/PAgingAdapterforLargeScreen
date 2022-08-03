package com.example.pagingadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import com.example.pagingadapter.adapters.PagingAdapter
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    lateinit var recyclerViewAdapter: PagingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerview = findViewById<RecyclerView>(R.id.recyclerView)

        recyclerViewAdapter = PagingAdapter()

        recyclerview.adapter = recyclerViewAdapter

        initViewModel()

    }

    private fun initViewModel() {
        val viewModel  = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                recyclerViewAdapter.submitData(it)
            }
        }
    }
}