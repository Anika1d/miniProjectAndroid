package com.example.shared.retrofit

import com.example.data.models.News
import retrofit2.http.GET

const val apiKey = "3f873ac76c71433ca11884814240540e"

interface RetrofitServices {

    @GET("v2/everything?q=apple&from=2022-03-20&to=2022-03-20&sortBy=popularity&apiKey=${apiKey}")
    suspend fun getAppleNews(): News

    @GET("v2/top-headlines?country=us&category=business&apiKey=${apiKey}")
    suspend fun getBusinessNews(): News

    @GET("v2/everything?q=tesla&from=2022-02-23&sortBy=publishedAt&apiKey=${apiKey}")
    suspend fun getTeslaNews(): News

    @GET("v2/everything?domains=wsj.com&apiKey=${apiKey}")
    suspend fun getWallStreetNews(): News

    @GET("v2/top-headlines?sources=techcrunch&apiKey=${apiKey}")
    suspend fun getTechCrunchNews(): News
}