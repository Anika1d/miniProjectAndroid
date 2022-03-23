package com.example.testwork

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import com.example.testwork.databinding.FragmentDetailsNewsBinding
import com.squareup.picasso.Picasso
import java.time.LocalDate
import java.time.format.DateTimeFormatter


private const val IMGURl_PARAM = "imgUrl"
private const val NEWSURl_PARAM = "newsUrl"
private const val AUTHOR_PARAM = "author"
private const val TITLE_PARAM = "title"
private const val CONTENT_PARAM = "content"
private const val DATE_PARAM = "date"

class DetailsNewsFragment() : Fragment() {

     private var _imgUrl: String? = null
    private var _author: String? = null
    private var _title: String? = null
    private var _content: String? = null
    private var _date: String? = null
    private var _newsUrls: String? = null
    private var _binding: FragmentDetailsNewsBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            _imgUrl = it.getString(IMGURl_PARAM)
            _content = it.getString(CONTENT_PARAM)
            _author = it.getString(AUTHOR_PARAM)
            _title = it.getString(TITLE_PARAM)
            _date = it.getString(DATE_PARAM)
            _newsUrls = it.getString(NEWSURl_PARAM)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsNewsBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("FragmentBackPressedCallback")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        with(binding) {
            content.text = _content
            nameAuthor.text = _author
            if (_imgUrl != "null")
                Picasso.get().load(_imgUrl).into(imageView)
            else
                imageView.setImageResource(R.drawable.ic_default_img)
            tittleNews.text = _title
            newsDate.text =
                LocalDate.parse(_date, formatter)
                    .format(DateTimeFormatter.ofPattern("dd-MM-yyyy"))
                    .toString()
            fullText.setOnClickListener {
                val address: Uri =
                    Uri.parse(_newsUrls)
                val openlink = Intent(Intent.ACTION_VIEW, address)
                startActivity(openlink)
            }
        }

        activity?.onBackPressedDispatcher?.addCallback(
            this,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    (activity as MainActivity?)!!.setVisibilityFragmentToGone()
                }

            })

    }
}