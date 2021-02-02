package com.internsaala.shamachar

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.littlemango.stacklayoutmanager.StackLayoutManager
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    lateinit var adapter: NewsAdapter
     private var articles = mutableListOf<Article>()

    var pageNum = 1
    var totalResults = -1
    val TAG = "MainActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = NewsAdapter(this@MainActivity, articles)
        val newsList: RecyclerView = findViewById(R.id.newsList)
        newsList.adapter = adapter
       // newsList.layoutManager = LinearLayoutManager(this@MainActivity)

        val layoutManager =StackLayoutManager(StackLayoutManager.ScrollOrientation.BOTTOM_TO_TOP)
        layoutManager.setPagerMode(true)
        layoutManager.setPagerFlingVelocity(3000)

        newsList.layoutManager = layoutManager
        layoutManager.setItemChangedListener(object : StackLayoutManager.ItemChangedListener{
            override fun onItemChanged(position: Int) {
                val container: ConstraintLayout = findViewById(R.id.container)
                container.setBackgroundColor(Color.parseColor(ColorPicker.getColor()))

                Log.d(TAG, "First Visible Item - ${layoutManager.getFirstVisibleItemPosition()}")
                Log.d(TAG, "Total Count - ${layoutManager.itemCount}")

                if(totalResults > layoutManager.itemCount && layoutManager.getFirstVisibleItemPosition() >= layoutManager.itemCount - 5){
                    pageNum++
                    getNews()
                }

            }

        })

        getNews()

    }

    private fun getNews() {

        Log.d(TAG, "Request Send for $pageNum")

        val news = NewsService.newsInstance.getHeadlines("in", pageNum)
        news.enqueue(object : Callback<News>{

            override fun onFailure(call: Call<News>, t: Throwable) {
                Log.d("NEWS APP", "ERROR IN FETCHING NEWS", t)
            }

            override fun onResponse(call: Call<News>, response: Response<News>) {
                val news = response.body()
                if(news != null){
                    totalResults = news.totalResults
                    articles.addAll(news.articles)
                    adapter.notifyDataSetChanged()
                }
            }

        })
    }
}