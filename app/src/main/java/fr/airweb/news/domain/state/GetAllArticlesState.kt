package fr.airweb.news.domain.state

import fr.airweb.news.data.model.ArticleEntity

sealed class GetAllArticlesState {
    data class ArticlesList(val newsList: List<ArticleEntity>) : GetAllArticlesState()
    object ArticlesListEmpty : GetAllArticlesState()
}
