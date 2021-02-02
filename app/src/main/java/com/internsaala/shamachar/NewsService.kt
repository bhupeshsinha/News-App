package com.internsaala.shamachar

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.stream.DoubleStream.builder

//http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=938da65530004d42bfe655862af2621e
//http://newsapi.org/v2/everything?q=apple&from=2021-01-30&to=2021-01-30&sortBy=popularity&apiKey=938da65530004d42bfe655862af2621e

//http://newsapi.org/v2/top-headlines?country=in&apiKey=API_KEY
//http://newsapi.org/v2/everything?q=apple&from=2021-01-30&to=2021-01-30&sortBy=popularity&apiKey=API_KEY

const val BASE_URL = "https://newsapi.org/"
const val API_KEY = "938da65530004d42bfe655862af2621e"

interface NewsInterface{

    @GET("v2/top-headlines?apiKey=$API_KEY")
    fun getHeadlines(@Query("country")country: String, @Query("page") page: Int): Call<News>


                                    /*SYNTAX*/
    //http://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=__&page=__
                                    /*EXAMPLE*/
    //http://newsapi.org/v2/top-headlines?apiKey=$API_KEY&country=in&page=1
}

object NewsService{
    val newsInstance: NewsInterface
    init {
        val retrofit = Retrofit.Builder()           //Object of Retrofit
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        newsInstance = retrofit.create(NewsInterface::class.java)
    }
}