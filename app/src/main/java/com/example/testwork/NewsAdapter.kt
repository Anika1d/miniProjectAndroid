package com.example.testwork

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.data.models.Article
import com.example.testwork.databinding.ItemNewsBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.collections.ArrayList


class NewsAdapter(
    private val actionListener: NewsActionListener
) : RecyclerView.Adapter<NewsAdapter.NewsHolder>(), View.OnClickListener {
    private val articleList = ArrayList<Article>()

    @SuppressLint("NotifyDataSetChanged")
    fun addArticleList(arrayL: List<Article>) {
        articleList.addAll(arrayL)
        notifyDataSetChanged()

    }

    @SuppressLint("NotifyDataSetChanged")
    fun clearAdapter() {
        articleList.clear()
        notifyDataSetChanged()
    }

    inner class NewsHolder(
        val binding: ItemNewsBinding
    ) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemNewsBinding.inflate(inflater, parent, false)
        binding.root.setOnClickListener(this)
        return NewsHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsHolder, position: Int) {
        val article = articleList[position]
        holder.itemView.tag = article
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        with(holder.binding) {
            if (article.urlToImage != null) {
                shimmerImg.startShimmer()
                Picasso.get().load(article.urlToImage).into(imageView)
                shimmerImg.stopShimmer()
            } else
                imageView.setImageResource(R.drawable.ic_default_img)
            shimmerImg.visibility = View.GONE
            imageView.visibility = View.VISIBLE
            tittleNews.text = article.title
            newsDate.text =
                LocalDate.parse(article.publishedAt.substring(0, 10), formatter)
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .toString()
        }
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    override fun onClick(p0: View) {
        val article: Article = p0.tag as Article
        actionListener.onDetails(article = article)
    }
}

interface NewsActionListener {
    fun onDetails(article: Article) {

    }
}