package fr.airweb.news.domain.state

import fr.airweb.news.data.model.NewsEntity

sealed class GetNewsFromTypeState {
    data class NewsList(val newsList: List<NewsEntity>) : GetNewsFromTypeState()
    object NewsListEmpty : GetNewsFromTypeState()
}