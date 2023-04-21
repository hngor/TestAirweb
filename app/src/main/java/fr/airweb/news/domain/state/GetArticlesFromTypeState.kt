package fr.airweb.news.domain.state

import fr.airweb.news.data.model.ArticleEntity

sealed class GetArticlesFromTypeState {
    data class ArticlesList(val newsList: List<ArticleEntity>) : GetArticlesFromTypeState()
    object ArticlesListEmpty : GetArticlesFromTypeState()
}