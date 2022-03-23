package com.example.shared.retrofit.repository

import com.example.data.models.News
import com.example.shared.repository.Repository
import com.example.shared.retrofit.RetrofitServices
import javax.inject.Inject

class RepositoryApiImpl @Inject constructor(private val retrofitServices: RetrofitServices) :
    Repository {
    override suspend fun getAppleNews(): News = retrofitServices.getAppleNews()

    override suspend fun getTeslaNews(): News = retrofitServices.getTeslaNews()

    override suspend fun getBusinessUSNews(): News =
        retrofitServices.getBusinessNews()

    override suspend fun getWallStreetNews(): News =
        retrofitServices.getWallStreetNews()

    override suspend fun getTechCrunchNews(): News =
        retrofitServices.getTechCrunchNews()
}