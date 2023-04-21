package fr.airweb.news.domain.state

import fr.airweb.news.data.model.ArticleEntity

sealed class GetSortedArticlesState {
    data class ArticlesList(val newsList: List<ArticleEntity>) : GetSortedArticlesState()
    object ArticlesListEmpty : GetSortedArticlesState()
}