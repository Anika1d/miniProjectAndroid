package com.example.testwork

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.data.models.Article
import com.example.testwork.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val viewModels: ActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private val newsAdapter = NewsAdapter(object : NewsActionListener {
        override fun onDetails(article: Article) {
            val bundle = Bundle()
            val fr = DetailsNewsFragment()
            bundle.putString("content", article.content)
            bundle.putString("newsUrl", article.url)
            if (article.urlToImage != null)
                bundle.putString("imgUrl", article.urlToImage)
            else
                bundle.putString("imgUrl", "null")
            if (article.author != null)
                bundle.putString("author", article.author)
            else
                bundle.putString("author", article.source.name)
            bundle.putString("title", article.title)
            bundle.putString("date", article.publishedAt.substring(0, 10))
            binding.recyclerView.visibility = View.GONE
            binding.fragmentContainerViewMaps.visibility = View.VISIBLE
            fr.arguments = bundle
            supportFragmentManager.beginTransaction().apply {
                replace(
                    R.id.fragmentContainerViewMaps,
                    fr,
                    "DetailsNewsFragment"
                )
                commit()
            }
        }
    })
    private var appleNewsList: List<Article>? = null
    private var businessNewsList: List<Article>? = null
    private var techCrunchNewsList: List<Article>? = null
    private var teslaNewsList: List<Article>? = null
    private var wallStreetNewsList: List<Article>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.adapter = newsAdapter
        startShimmerAnimate()
        drawAppleNews()
        binding.btnNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.apple -> {
                    setVisibilityFragmentToGone()
                    startShimmerAnimate()
                    drawAppleNews()
                }
                R.id.business -> {
                    setVisibilityFragmentToGone()
                    startShimmerAnimate()
                    drawBusinessNews()
                }
                R.id.journal -> {
                    setVisibilityFragmentToGone()
                    startShimmerAnimate()
                    drawWallStreetNews()
                }
                R.id.techcrunch -> {
                    setVisibilityFragmentToGone()
                    startShimmerAnimate()
                    drawTechCrunchNews()
                }
                R.id.tesla -> {
                    setVisibilityFragmentToGone()
                    startShimmerAnimate()
                    drawTeslaNews()
                }
            }
            true

        }
    }

    private fun stopShimmerAnimate() {
        binding.apply {
            shimmerRec.stopShimmer()
            shimmerRec.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }
    }

    private fun startShimmerAnimate() {
        binding.apply {
            shimmerRec.startShimmer()
            shimmerRec.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }
    }

    private fun drawAppleNews() {
        viewModels.getNews("Apple").observe(this) {
            appleNewsList = it
            binding.apply {
                nameNews.text = resources.getString(R.string.apple)
                appleNewsList?.let { it ->
                    newsAdapter.apply {
                        clearAdapter()
                        addArticleList(it)
                    }

                }
                stopShimmerAnimate()
            }
        }

    }

    private fun drawBusinessNews() {
        viewModels.getNews("Business").observe(this) {
            businessNewsList = it
            binding.apply {
                nameNews.text = resources.getString(R.string.business_usa)
                businessNewsList?.let { it ->
                    newsAdapter.apply {
                        clearAdapter()
                        addArticleList(it)
                    }

                }
                stopShimmerAnimate()
            }
        }
    }

    private fun drawTechCrunchNews() {
        viewModels.getNews("TechCrunch").observe(this) {
            techCrunchNewsList = it
            binding.apply {
                nameNews.text = resources.getString(R.string.techcrunch)
                techCrunchNewsList?.let { it ->
                    newsAdapter.apply {
                        clearAdapter()
                        addArticleList(it)
                    }
                }
                stopShimmerAnimate()
            }
        }
    }

    private fun drawWallStreetNews() {
        viewModels.getNews("WallStreet").observe(this) {
            wallStreetNewsList = it
            binding.apply {
                nameNews.text = resources.getString(R.string.wall_street)
                wallStreetNewsList?.let { it ->
                    newsAdapter.apply {
                        clearAdapter()
                        addArticleList(it)
                    }
                }
                stopShimmerAnimate()
            }
        }
    }

    private fun drawTeslaNews() {
        viewModels.getNews("Tesla").observe(this) {
            teslaNewsList = it
            binding.apply {
                nameNews.text = resources.getString(R.string.tesla)
                teslaNewsList?.let { it ->
                    newsAdapter.apply {
                        clearAdapter()
                        addArticleList(it)
                    }
                }
                stopShimmerAnimate()
            }
        }
    }

    fun setVisibilityFragmentToGone() {
        if (binding.fragmentContainerViewMaps.visibility != View.GONE) {
            binding.recyclerView.visibility = View.VISIBLE
            binding.fragmentContainerViewMaps.visibility = View.GONE
        }
    }
}