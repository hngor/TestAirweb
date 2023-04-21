package fr.airweb.news.domain.state

import fr.airweb.news.data.model.ArticleEntity

sealed class SearchArticlesState {
    data class ArticlesList(val newsList: List<ArticleEntity>) : SearchArticlesState()
    object ArticlesListEmpty : SearchArticlesState()
}