package com.danny.myapplication.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.danny.myapplication.R
import com.danny.myapplication.RecyclerAdapter
import com.danny.myapplication.RetrofitService
import kotlinx.android.synthetic.main.fragment_third.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ThirdFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    lateinit var adapter: RecyclerAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val client = Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        val service = client.create(RetrofitService::class.java)
        CoroutineScope(Dispatchers.IO).launch {
            service.getPosts().body()?.let { post ->
                CoroutineScope(Dispatchers.Main).launch {
                    if (post.size == 0) {
                        no_item.visibility = View.VISIBLE
                    } else {
                        no_item.visibility = View.GONE
                        adapter = RecyclerAdapter(post)
                        recycler.adapter = adapter
                        recycler.layoutManager = LinearLayoutManager(this@ThirdFragment.activity)
                    }
                }
            }
        }

    }
}