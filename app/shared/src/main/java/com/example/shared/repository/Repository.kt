package com.example.shared.repository

import com.example.data.models.News

interface Repository {
    suspend fun getAppleNews(): News
    suspend fun getTeslaNews(): News
    suspend fun getBusinessUSNews(): News
    suspend fun getWallStreetNews(): News
    suspend fun getTechCrunchNews(): News
}