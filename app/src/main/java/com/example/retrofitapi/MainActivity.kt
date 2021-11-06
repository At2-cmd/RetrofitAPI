package com.example.retrofitapi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    lateinit var postService: PostService
    lateinit var postList: List<Post>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        postService = ApiClient.getClient().create(PostService::class.java)
        var post = postService.listPost("1")

        post.enqueue(object : Callback<List<Post>> {
            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                Toast.makeText(applicationContext, t.message.toString(), Toast.LENGTH_LONG).show()
            }


            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    postList = (response.body() as MutableList<Post>?)!!
                }
            }
        })

        // getting the recyclerview by its id
        val recyclerview = findViewById<RecyclerView>(R.id.recyclerview)

        // this creates a vertical layout Manager
        recyclerview.layoutManager = LinearLayoutManager(this)

        // ArrayList of class ItemsViewModel
        val data = ArrayList<Post>()

        // This will pass the ArrayList to our Adapter
        val adapter = CustomAdapter(data)

        // Setting the Adapter with the recyclerview
        recyclerview.adapter = adapter

    }
    }

