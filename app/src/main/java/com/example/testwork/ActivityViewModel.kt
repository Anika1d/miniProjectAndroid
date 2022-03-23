package com.example.testwork

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.models.Article
import com.example.data.models.News
import com.example.shared.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class ActivityViewModel @Inject constructor(
    private val useCaseAppleNews: UseCaseAppleNews,
    private val useCaseBusinessHeadlinesUS: UseCaseBusinessHeadlinesUS,
    private val useCaseTechCrunchNews: UseCaseTechCrunchNews,
    private val useCaseTeslaNews: UseCaseTeslaNews,
    private val useCaseWallStreetJournalNews: UseCaseWallStreetJournalNews
) : ViewModel() {
    private val articleList = MutableLiveData<List<Article>>()
    private var appleNews: List<Article>? = null
    private var businessNews: List<Article>? = null
    private var techCrunchNews: List<Article>? = null
    private var teslaNews: List<Article>? = null
    private var wallStreetNews: List<Article>? = null


    private suspend fun uploadNews(nameSource: String): News {
        val news: News = when (nameSource) {
            "Apple" -> useCaseAppleNews.invoke()
            "Tesla" -> useCaseTeslaNews.invoke()
            "TechCrunch" -> useCaseTechCrunchNews.invoke()
            "Business" -> useCaseBusinessHeadlinesUS.invoke()
            else -> useCaseWallStreetJournalNews.invoke()
        }
        return news
    }

    fun getNews(nameSource: String): LiveData<List<Article>> {
        viewModelScope.launch {
            articleList.value = when (nameSource) {
                "Apple" -> if (appleNews != null) {
                    appleNews
                } else {
                    uploadArticlesApple()
                    appleNews
                }
                "Tesla" -> {
                    if (teslaNews != null) {
                        teslaNews
                    } else {
                        uploadArticlesTesla()
                        teslaNews
                    }
                }
                "TechCrunch" -> {
                    if (techCrunchNews != null) {
                        techCrunchNews
                    } else {
                 uploadArticlesTechCrunch()
                        techCrunchNews
                    }
                }
                "Business" -> {
                    if (businessNews != null) {
                        businessNews
                    } else {
            uploadArticlesBusiness()
                        businessNews
                    }
                }
                else -> {
                    if (wallStreetNews != null) {
                        wallStreetNews
                    } else {
                    uploadArticlesWallStreet()
                        wallStreetNews
                    }

                }
            }
        }
        return articleList
    }

    private suspend fun uploadArticlesApple() {
        appleNews = uploadNews("Apple").articles
    }

    private suspend fun uploadArticlesBusiness() {
        businessNews = uploadNews("Business").articles
    }

    private suspend fun uploadArticlesTesla() {
        teslaNews = uploadNews("Tesla").articles
    }

    private suspend fun uploadArticlesWallStreet() {
        wallStreetNews = uploadNews("WallStreet").articles
    }

    private suspend fun uploadArticlesTechCrunch() {
        techCrunchNews = uploadNews("TechCrunch").articles
    }
}
