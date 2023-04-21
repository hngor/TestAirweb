package fr.airweb.news.domain.state

import fr.airweb.news.data.model.NewsEntity

sealed class SearchNewsState {
    data class NewsList(val newsList: List<NewsEntity>) : SearchNewsState()
    object NewsListEmpty : SearchNewsState()
}