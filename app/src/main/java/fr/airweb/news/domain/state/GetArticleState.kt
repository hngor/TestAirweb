package fr.airweb.news.domain.state

import fr.airweb.news.data.model.ArticleEntity

sealed class GetArticleState {
    data class Article(val article: ArticleEntity) : GetArticleState()
    object NoArticle : GetArticleState()
}
