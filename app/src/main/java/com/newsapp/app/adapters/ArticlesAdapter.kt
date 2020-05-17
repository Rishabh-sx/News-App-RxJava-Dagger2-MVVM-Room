package com.newsapp.app.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.newsapp.app.R
import com.newsapp.app.adapters.ArticlesAdapter.HeadlinesViewHolder
import com.newsapp.app.dataclass.Article
import com.newsapp.app.util.AppUtils
import kotlinx.android.synthetic.main.row_headlines.view.*
import javax.inject.Inject


class ArticlesAdapter @Inject constructor(var articleListener: ArticleListener,var articleList: MutableList<Article>) :
    RecyclerView.Adapter<HeadlinesViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): HeadlinesViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.row_headlines, viewGroup, false)
        return HeadlinesViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: HeadlinesViewHolder, i: Int) {
        viewHolder.bind(articleList[i])
    }

    override fun getItemCount(): Int {
        return articleList.size
    }

    fun setAdapterList(articles: List<Article>?) {
        articleList.clear()
        articleList.addAll(articles!!)
        notifyDataSetChanged()
    }

    inner class HeadlinesViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView), View.OnClickListener {

        fun bind(article: Article) {
            Glide.with(itemView.context).asBitmap().thumbnail(0.1f).load(article.urlToImage).into(itemView.image)
            itemView.title!!.text = article.title
            itemView.time!!.text = AppUtils.formatDate(article.publishedAt)
            itemView.setOnClickListener(this)
            itemView.share!!.setOnClickListener { articleListener.share(article.url!!)}
        }

        override fun onClick(view: View) {
            articleListener.itemClicked(articleList[adapterPosition])
        }
    }

    interface ArticleListener {
        fun itemClicked(result: Article)
        fun share(url: String)
    }
}