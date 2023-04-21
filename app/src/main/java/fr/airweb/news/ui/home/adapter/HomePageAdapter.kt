package fr.airweb.news.ui.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.airweb.news.R
import fr.airweb.news.ui.home.model.NewsUiModel

class HomePageAdapter(
    private val onNewsClick: ((Int) -> Unit)?
) : RecyclerView.Adapter<NewsArticleViewHolder>() {

    internal var articleList: List<NewsUiModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsArticleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.news_card, parent, false)
        return NewsArticleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return articleList.count()
    }

    override fun onBindViewHolder(holder: NewsArticleViewHolder, position: Int) {
        val article = articleList[position]

        holder.txtNewsArticle.text = article.title
        Glide.with(holder.itemView).load(article.picture).into(holder.imgNewsArticle)

        holder.itemView.setOnClickListener { onNewsClick?.invoke(article.id) }
    }

}

class NewsArticleViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    var txtNewsArticle: TextView = view.findViewById(R.id.txtNewsArticleTitle)
    var imgNewsArticle: ImageView = view.findViewById(R.id.imgNewsArticle)
}