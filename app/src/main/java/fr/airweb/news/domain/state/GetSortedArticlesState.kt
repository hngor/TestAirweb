package fr.airweb.news.domain.state

import fr.airweb.news.data.model.NewsEntity

sealed class GetSortedNewsState {
    data class NewsList(val newsList: List<NewsEntity>) : GetSortedNewsState()
    object NewsListEmpty : GetSortedNewsState()
}